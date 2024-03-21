package app.controllers;

import app.entities.MatchMakerUser;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MatchmakerMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class MatchmakerController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/frontpage", ctx -> index(ctx, connectionPool));
        app.post("/loginpage", ctx -> login(ctx, connectionPool));
        app.post("createuserpage", ctx -> ctx.render("/matchmaker/matchmakercreateuser.html"));
        app.post("/signup",ctx->createUser(ctx,connectionPool));
       // app.post("matchmaker/createuserpage",ctx-> createPreference(ctx,connectionPool));
        app.post("/swipe.html", ctx -> likeFugitive(ctx, connectionPool));
        app.get("/swipe/html", ctx -> showFugitivePhoto(ctx, connectionPool));

    }

    private static void matchmakercreateuserpage(Context ctx, ConnectionPool connectionPool) {
        ctx.render("/matchmaker/matchmakercreateuser.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
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
            MatchMakerUser user = MatchmakerMapper.login(username, password, connectionPool);
            ctx.sessionAttribute("currentUser", user);
            // Hvis ja, send videre til forsiden med login besked
            ctx.attribute("message", "Du er nu logget ind");
            ctx.render("matchmaker/index.html");
        }
        catch (DatabaseException e)
        {
            // Hvis nej, send tilbage til login side med fejl besked
            ctx.attribute("message", e.getMessage() );
            ctx.render("index.html");
        }

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
        String gender=ctx.formParam("gender");


        if (password1.equals(password2))
        {
            try
            {
                MatchmakerMapper.createuser(username, password1,firstName,lastName,age,gender, connectionPool);
                ctx.attribute("message", "Du er hermed oprettet med brugernavn: " + username +
                        ". Nu skal du logge på.");
                createPreference(ctx,connectionPool);
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
    private static void createPreference(Context ctx, ConnectionPool connectionPool){
        String hairColor = ctx.formParam("haircolor");
        String eyeColor = ctx.formParam("eyecolor");
        String sex = ctx.formParam("sex");
        String race = ctx.formParam("race");

        try {

            MatchmakerMapper.createPreference(hairColor, eyeColor, sex, race, connectionPool);
            ctx.render("createuser.html");
        }catch (DatabaseException e){
            ctx.render("index.html");
        }

        System.out.println("hej");
    }

    private static void likeFugitive(Context ctx, ConnectionPool connectionPool){
        int userId = ctx.sessionAttribute("currentUser");
        int fugitiveId = Integer.parseInt(ctx.formParam("fk_fugitive"));

        try{
            MatchmakerMapper.likeFugitive(userId, fugitiveId, connectionPool);
            ctx.attribute("Fugitive liked");
            ctx.render("/swipe.html");
        } catch (DatabaseException e){
            ctx.attribute("Fejl vel liket, prøv igen", e.getMessage());
            ctx.render("/swipe.html");
        }

    }

    private static void showFugitivePhoto(Context ctx, ConnectionPool connectionPool){
        int fugitiveId = Integer.parseInt(ctx.formParam("fugitive_id"));

        try{
            String photoURL = MatchmakerMapper.getPhotoURL(fugitiveId,connectionPool);
            ctx.html("<img src='" + photoURL + "' alt='Fugitive Photo'>");
        }catch (DatabaseException e){
            ctx.attribute("Fejl omkring billede");

        }
    }

}