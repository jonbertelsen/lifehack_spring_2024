package app.controllers;

import app.entities.FriskForslagRecipe;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.FriskForslagMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class FriskForslagController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/friskforslag", ctx -> index(ctx, connectionPool));
        app.get("/friskforslag.html", ctx -> index(ctx, connectionPool));
        app.get("/friskforslag/search", ctx -> search(ctx, connectionPool));
        app.get("/friskforslag/search.html", ctx -> search(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/friskforslag/index.html");
    }

    private static void search(Context ctx, ConnectionPool connectionPool)
    {
        String qParams = ctx.queryParam("ingredients");
        if (qParams == null || qParams.isEmpty())
            ctx.attribute("message", "ugyldig søgning");
        else {
            String[] ingredients = qParams.split(",");
            try {
                List<FriskForslagRecipe> filteredRecipes =
                        FriskForslagMapper.FilterRecipesIngredients(connectionPool, ingredients);
                ctx.attribute("filteredRecipes", filteredRecipes);
            }
            catch (DatabaseException e) {
                ctx.attribute("message", "fejl i søgning af database");
            }

        }

        ctx.render("/friskforslag/search.html");
    }
}