package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class SavingsCalculatorController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/savingscalculator", ctx -> index(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/savingscalculator/index.html");
    }
}