package app.controllers;

import app.entities.PizzaRecipe;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.PizzaCalculatorMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.util.List;

public class PizzaCalculatorController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("pizzacalculator", ctx -> index(ctx, connectionPool));
        app.post("pizzacalculator", ctx -> makeRecipe(ctx, connectionPool));
        app.get("pizzacalculator/archive", ctx -> archive(ctx, connectionPool));
        app.post("pizzacalculator/delete", ctx -> deleteRecipe(ctx, connectionPool));
        app.get("pizzacalculator/create", ctx -> createRecipe(ctx, connectionPool));
    }

    private static void createRecipe(Context ctx, ConnectionPool connectionPool)
    {
        PizzaRecipe recipe = ctx.sessionAttribute("recipe");
        User user = ctx.sessionAttribute("currentUser");
        if (recipe != null && user != null)
        {
            try
            {
                PizzaCalculatorMapper.createRecipe(recipe, user.getUserId(),  connectionPool);
                List<PizzaRecipe> recipeList = PizzaCalculatorMapper.getRecipes(user.getUserId(), connectionPool);
                ctx.attribute("recipeList", recipeList);
            }
            catch (DatabaseException e)
            {
                throw new RuntimeException(e);
            }
        } else {
            ctx.attribute("message","You need to login to save a recipe");
            ctx.render("/pizzacalculator/index.html");
            return;
        }
        ctx.render("/pizzacalculator/archive.html");
    }

    private static void deleteRecipe(Context ctx, ConnectionPool connectionPool)
    {
        try {
            int recipeId = Integer.parseInt(ctx.formParam("recipeId"));
            PizzaCalculatorMapper.deleteRecipe(recipeId, connectionPool);
            User user = ctx.sessionAttribute("currentUser");
            List<PizzaRecipe> recipeList = PizzaCalculatorMapper.getRecipes(user.getUserId(), connectionPool);
            ctx.attribute("recipeList", recipeList);
            ctx.render("/pizzacalculator/archive.html");
        } catch (NumberFormatException e)
        {
            ctx.status(400);
            ctx.result("Invalid input");
        }
        catch (DatabaseException e)
        {
            throw new RuntimeException(e);
        }
    }


    private static void archive(Context ctx, ConnectionPool connectionPool)
    {
        try
        {
            User user = ctx.sessionAttribute("currentUser");
            if (user != null)
            {
                List<PizzaRecipe> recipeList = PizzaCalculatorMapper.getRecipes(user.getUserId(), connectionPool);
                ctx.attribute("recipeList", recipeList);
                ctx.render("/pizzacalculator/archive.html");
                return;
            } else {
                ctx.attribute("message","You need to login to view the archive");
                ctx.render("/pizzacalculator/index.html");
                return;
            }
        }
        catch (DatabaseException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void makeRecipe(Context ctx, ConnectionPool connectionPool)
    {
        String idString = ctx.formParam("recipeId");
        if (idString != null)
        {
                int recipeId = Integer.parseInt(idString);
            PizzaRecipe recipe = null;
            try
            {
                recipe = PizzaCalculatorMapper.getRecipeById(recipeId, connectionPool);
            }
            catch (DatabaseException e)
            {
                throw new RuntimeException(e);
            }
            ctx.sessionAttribute("recipe", recipe);
                ctx.render("/pizzacalculator/recipe.html");
                return;
        }

        PizzaRecipe recipe = ctx.sessionAttribute("recipe");
        try
        {
            int quantity = Integer.parseInt(ctx.formParam("quantity"));
            int weight = Integer.parseInt(ctx.formParam("weight"));
            int hydration = Integer.parseInt(ctx.formParam("hydration"));
            int temperature = Integer.parseInt(ctx.formParam("temperature"));

            recipe = new PizzaRecipe(quantity, weight, hydration, temperature);
            ctx.sessionAttribute("recipe", recipe);
        } catch (NumberFormatException e)
        {
            if (recipe == null)
            {
                ctx.status(400);
                ctx.result("Invalid input");
                return;
            }
        }
        ctx.render("/pizzacalculator/recipe.html");
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/pizzacalculator/index.html");
    }
}
