package app.controllers;

import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class CalorieCruncherController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/caloriecruncher", ctx -> index(ctx, connectionPool));
        app.post("/caloriecruncherCalculate", ctx -> calculate(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/caloriecruncher/index.html");
    }

    private static void calculate(Context ctx, ConnectionPool connectionPool) {
        String goal = ctx.formParam("Goal");
        String gender = ctx.formParam("Gender");
        int age = Integer.parseInt(ctx.formParam("Age"));
        int weight = Integer.parseInt(ctx.formParam("Weight"));
        int height = Integer.parseInt(ctx.formParam("Height"));

        int bmr = 0;

        if (gender.equals("Male")) {
            bmr = (int) (66.5f + (13.75f * weight) + (5.003f * height) - (6.75f * age));
        } else if (gender.equals("Female")) {
            bmr = (int) (655.1f + (9.563f * weight) + (1.850f * height) - (4.676f * age));
        }
        ctx.attribute("BMR",bmr);
        if (goal.equals("gain")) {
            bmr += 500;
        }
        else if (goal.equals("cut")) {
            bmr -= 500;
        }


        ctx.attribute("Goal", "Daily calories your body need to " + goal + "!");
        ctx.attribute("BMRGoal", bmr);
        ctx.render("/caloriecruncher/cc.html");
    }
}
