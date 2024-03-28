package app.controllers;

import app.entities.Calculation;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class SavingsCalculatorController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/savingscalculator", ctx -> index(ctx, connectionPool));
        app.post("savingscalculator/calculate", ctx ->calculate(ctx,connectionPool) );
    }

    private static void calculate(Context ctx, ConnectionPool connectionPool) {

        int savingPercentage = Integer.parseInt(ctx.formParam("savingPercentage"));
        int income = Integer.parseInt(ctx.formParam("income"));
        int rent=Integer.parseInt(ctx.formParam("rent"));
        int goal=Integer.parseInt(ctx.formParam("goal"));

        Calculation calculation = new Calculation(savingPercentage,income,rent,goal);

        ctx.attribute("calculation",calculation);

        ctx.render("/savingscalculator/calculate.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/savingscalculator/index.html");
    }
}