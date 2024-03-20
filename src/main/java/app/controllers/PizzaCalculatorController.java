package app.controllers;

import app.entities.PizzaRecipe;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.time.LocalDate;

public class PizzaCalculatorController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/pizzacalculator", ctx -> index(ctx, connectionPool));
        app.post("/pizzacalculator", ctx -> makeRecipe(ctx, connectionPool));
    }

    private static void makeRecipe(Context ctx, ConnectionPool connectionPool)
    {
        int quantity = Integer.parseInt(ctx.formParam("quantity"));
        int weight = Integer.parseInt(ctx.formParam("weight"));
        int hydration = Integer.parseInt(ctx.formParam("hydration"));
        int temperature = Integer.parseInt(ctx.formParam("temperature"));

        PizzaRecipe recipe = new PizzaRecipe(quantity, weight, hydration, temperature);
        // Save the recipe to the database
        ctx.sessionAttribute("recipe", recipe);
        ctx.render("/pizzacalculator/recipe.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/pizzacalculator/index.html");
    }
}
