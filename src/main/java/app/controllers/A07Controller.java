package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class A07Controller
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/A07", ctx -> index(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/A07/index.html");
    }
}