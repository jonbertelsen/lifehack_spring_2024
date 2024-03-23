package app;

import app.config.ThymeleafConfig;
import app.controllers.A07Controller;
import app.persistence.GalgeSpil;
import app.controllers.HaikuController;
import app.controllers.FactController;
import app.controllers.FriskForslagController;
import app.controllers.MatchmakerController;
import app.controllers.PlantPalController;
import app.controllers.CalorieCruncherController;
import app.controllers.PizzaCalculatorController;
import app.controllers.TimeZonesController;
import app.controllers.UserController;
import app.persistence.ConnectionPool;
import app.persistence.HaikuMapper;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main
{
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "lifehack";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);


    public static void main(String[] args)
    {
        // Initializing Javalin and Jetty webserver
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing
        app.get("/", ctx -> ctx.render("index.html"));
        UserController.addRoutes(app, connectionPool);
        TimeZonesController.addRoutes(app, connectionPool);
        A07Controller.addRoutes(app, connectionPool, new GalgeSpil());
        HaikuController.addRoutes(app,connectionPool);
        FactController.addRoutes(app, connectionPool);
        FriskForslagController.addRoutes(app, connectionPool);
        MatchmakerController.addRoutes(app,connectionPool);
        PlantPalController.addRoutes(app,connectionPool);
        CalorieCruncherController.addRoutes(app, connectionPool);
        PizzaCalculatorController.addRoutes(app, connectionPool);
    }
}