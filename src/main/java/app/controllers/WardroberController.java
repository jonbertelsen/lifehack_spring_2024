package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;

public class WardroberController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/wardrober", ctx -> index(ctx, connectionPool));
    }
}