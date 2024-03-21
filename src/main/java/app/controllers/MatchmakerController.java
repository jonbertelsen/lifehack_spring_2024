package app.controllers;

import app.entities.MatchMakerUser;
import app.entities.MatchmakerFugitive;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.MatchmakerMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

public class MatchmakerController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/frontpage", ctx -> index(ctx, connectionPool));
        app.post("/swipe", ctx -> login(ctx, connectionPool));
        app.post("/createuserpage", ctx -> ctx.render("/matchmaker/matchmakercreateuser.html"));
        app.post("/signup", ctx -> createUser(ctx, connectionPool));
        //app.post("/swipe.html", ctx -> likeFugitive(ctx, connectionPool));
      //  app.post("/swipepage", ctx -> showFugitivePhoto(ctx, connectionPool));
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
        try {
            // Authenticate user
            MatchMakerUser user = MatchmakerMapper.login(username, password, connectionPool);

            // If user is authenticated, set user details in session
            if (user != null) {
                ctx.sessionAttribute("currentUser", user);
                ctx.attribute("message", "Du er nu logget ind");
                ctx.render("matchmaker/swipe.html");
                showFugitivePhoto(ctx,connectionPool);
            } else {
                ctx.attribute("message", "Brugernavn eller adgangskode er forkert.");
                ctx.render("matchmaker/index.html");
            }
        } catch (DatabaseException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("matchmaker/index.html");
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
                int userid=MatchmakerMapper.createuser(username, password1,firstName,lastName,age,gender, connectionPool);
                ctx.attribute("message", "Du er hermed oprettet med brugernavn: " + username +
                        ". Nu skal du logge på.");
                createPreference(ctx,connectionPool,userid);
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
    private static void createPreference(Context ctx, ConnectionPool connectionPool, int userid){
        String hairColor = ctx.formParam("haircolor");
        String eyeColor = ctx.formParam("eyecolor");
        String sex = ctx.formParam("sex");

        try {

            MatchmakerMapper.createPreference(userid,hairColor, eyeColor, sex,connectionPool);
            ctx.render("createuser.html");
        }catch (DatabaseException e){
            ctx.render("index.html");
        }


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

    private static void showFugitivePhoto(Context ctx, ConnectionPool connectionPool) {
        MatchMakerUser currentUser = ctx.sessionAttribute("currentUser");

        if (currentUser == null) {
            ctx.html("Error: User not logged in");
            return;
        }

        try {
            int userId = currentUser.getUserId();
            MatchmakerFugitive photurl=MatchmakerMapper.getphoturlAndFugitives_id(userId,connectionPool);
            System.out.println(photurl.getUrl());
            String  photourl= photurl.getUrl();

            saveImage(photourl);

 //           int fugitiveId = MatchmakerMapper.getRandomFugitiveIdForUser(userId, connectionPool);
   //         String photoURL = MatchmakerMapper.getPhotoURL(fugitiveId, connectionPool);
  //          ctx.html("<img src='" + photoURL + "' alt='Fugitive Photo'>");
        } catch (DatabaseException | IOException e) {
            ctx.html("Error: " + e.getMessage());
        }
    }



        public static void saveImage(String imageUrl) throws IOException {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            try (InputStream inputStream = connection.getInputStream();
                 OutputStream outputStream = new FileOutputStream("src/main/resources/public/images/matchmaker/temp.jpg")) {

                byte[] buffer = new byte[2048];
                int length;

                while ((length = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.close();
            } finally {
                connection.disconnect();
            }


    }




}