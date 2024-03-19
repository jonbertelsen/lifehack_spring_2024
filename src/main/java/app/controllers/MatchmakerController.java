package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MatchmakerMapper;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class MatchmakerController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/projectname", ctx -> index(ctx, connectionPool));
        app.get("matchmaker/createuser", ctx-> createUser(ctx,connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/projectname/index.html");
    }
    private static void createUser(Context ctx, ConnectionPool connectionPool)
    {
        // Hent form parametre
        String username = ctx.formParam("username");
        String password1 = ctx.formParam("password1");
        String password2 = ctx.formParam("password2");
        String firstName = ctx.formParam("firstName");
        String lastName = ctx.formParam("lastName");
        int age = Integer.parseInt(ctx.formParam("age"));
        String gender=ctx.formParam("gender");




        if (password1.equals(password2))
        {
            try
            {
                MatchmakerMapper.createuser(username, password1,firstName,lastName,age,gender, connectionPool);
                ctx.attribute("message", "Du er hermed oprettet med brugernavn: " + username +
                        ". Nu skal du logge på.");
                ctx.render("index.html");
            }

            catch (DatabaseException e)
            {
                ctx.attribute("message", "Dit brugernavn findes allerede. Prøv igen, eller log ind");
                ctx.render("createuser.html");
            }
        } else
        {
            ctx.attribute("message", "Dine to passwords matcher ikke! Prøv igen");
            ctx.render("createuser.html");
        }

    }



}