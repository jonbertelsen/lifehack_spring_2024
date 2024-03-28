package app.controllers;

import app.entities.MatchMakerAffinity;
import app.entities.MatchMakerUser;
import app.entities.MatchmakerFugitive;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MatchmakerMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class MatchmakerController
{

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/frontpage", ctx -> index(ctx, connectionPool));
        app.post("/loginn", ctx -> login(ctx, connectionPool));
        app.post("/createuserpage", ctx -> ctx.render("/matchmaker/matchmakercreateuser.html"));
        app.post("/signup", ctx -> createUser(ctx, connectionPool));
        app.post("swipefordislike", ctx -> renderSwipePage(MatchMakerAffinity.DISLIKE, ctx, connectionPool));
        app.post("swipeforlike", ctx -> renderSwipePage(MatchMakerAffinity.LIKE ,ctx, connectionPool));
        //app.post("/swipe.html", ctx -> likeFugitive(ctx, connectionPool));
        //  app.post("/swipepage", ctx -> showFugitivePhoto(ctx, connectionPool));
    }

    private static void matchmakercreateuserpage(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/matchmaker/matchmakercreateuser.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/matchmaker/index.html");

    }

    public static void login(Context ctx, ConnectionPool connectionPool)
    {
        // Hent form parametre
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // Check om bruger findes i DB med de angivne username + password
        try
        {
            // Authenticate user
            MatchMakerUser currentUser = MatchmakerMapper.login(username, password, connectionPool);

            // If user is authenticated, set user details in session
            if (currentUser != null)
            {
                ctx.sessionAttribute("currentUser", currentUser);
                List<MatchmakerFugitive> fugitiveList = MatchmakerMapper.getphoturlAndFugitives_id(currentUser.getUserId(), connectionPool);
                ctx.sessionAttribute("fugitiveList", fugitiveList);
                ctx.sessionAttribute("currentFugitiveCount", -1);
                ctx.attribute("message", "Du er nu logget ind");
                renderSwipePage(MatchMakerAffinity.DISLIKE,ctx, connectionPool);

            } else
            {
                ctx.attribute("message", "Brugernavn eller adgangskode er forkert.");
                ctx.render("matchmaker/index.html");
            }
        }
        catch (DatabaseException e)
        {
            ctx.attribute("message", e.getMessage());
            ctx.render("matchmaker/index.html");
        }

    }

    private static void renderSwipePage(MatchMakerAffinity affinity,  Context ctx, ConnectionPool connectionPool)
    {
        MatchMakerUser currentUser = ctx.sessionAttribute("currentUser");

        List<MatchmakerFugitive> fugitiveList = ctx.sessionAttribute("fugitiveList");
        int currentFugitiveCount = 0;
        try
        {
            int count = ctx.sessionAttribute("currentFugitiveCount");
            currentFugitiveCount = count;
            currentFugitiveCount = (currentFugitiveCount + 1) % fugitiveList.size();
            ctx.sessionAttribute("currentFugitiveCount", currentFugitiveCount);
            if (affinity == MatchMakerAffinity.LIKE)
            {
                int fugitiveId = Integer.parseInt(ctx.formParam("fugitiveId"));
                MatchmakerMapper.likeFugitive(currentUser.getUserId(), fugitiveId, connectionPool);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ctx.sessionAttribute("currentFugitiveCount", 0);
        }

        MatchmakerFugitive fugitive = fugitiveList.get(currentFugitiveCount);
        ctx.attribute("fugitive", fugitive);
        ctx.render("matchmaker/swipe.html");
    }

    private static void createUser(Context ctx, ConnectionPool connectionPool)
    {

        // Hent form parametre
        String username = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");
        String firstName = ctx.formParam("firstname");
        String lastName = ctx.formParam("lastname");
        int age = Integer.parseInt(ctx.formParam("age"));
        String gender = ctx.formParam("gender");

        if (password1.equals(password2))
        {
            try
            {
                int userid = MatchmakerMapper.createuser(username, password1, firstName, lastName, age, gender, connectionPool);
                ctx.attribute("message", "Du er hermed oprettet med brugernavn: " + username +
                        ". Nu skal du logge på.");
                createPreference(ctx, connectionPool, userid);
                ctx.render("/matchmaker/index.html");
            }
            catch (DatabaseException e)
            {
                ctx.attribute("message", "Dit brugernavn findes allerede. Prøv igen, eller log ind");
                ctx.render("/matchmaker/matchmakercreateuser.html");
            }
        } else
        {
            ctx.attribute("message", "Dine to passwords matcher ikke! Prøv igen");
            ctx.render("/matchmaker/matchmakercreateuser.html");
        }

    }

    private static void createPreference(Context ctx, ConnectionPool connectionPool, int userid)
    {
        String hairColor = ctx.formParam("haircolor");
        String eyeColor = ctx.formParam("eyecolor");
        String sex = ctx.formParam("sex");

        try
        {

            MatchmakerMapper.createPreference(userid, hairColor, eyeColor, sex, connectionPool);
            ctx.render("createuser.html");
        }
        catch (DatabaseException e)
        {
            ctx.render("index.html");
        }
    }

}