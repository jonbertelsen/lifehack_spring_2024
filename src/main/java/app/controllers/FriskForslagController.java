package app.controllers;

import app.entities.FriskForslagRecipe;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.FriskForslagMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FriskForslagController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/friskforslag", ctx -> index(ctx, connectionPool));
        app.get("/friskforslag.html", ctx -> index(ctx, connectionPool));
        app.get("/friskforslag/search", ctx -> search(ctx, connectionPool));
        app.get("/friskforslag/search.html", ctx -> search(ctx, connectionPool));
        app.get("/friskforslag/recipes/*", ctx -> findRecipe(ctx, connectionPool));
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
            ctx.attribute("searchParams", qParams);
            String[] ingredients = qParams.split(" ");
            try {
                List<FriskForslagRecipe> filteredRecipes =
                        FriskForslagMapper.FilterRecipesIngredients(connectionPool, ingredients);

                // indicator list for matching ingredients
                List<List<Boolean>> indicatorList = new ArrayList<>();
                for (FriskForslagRecipe rec : filteredRecipes){
                    List<Boolean> matchList = new ArrayList<>();
                    for (String ing : rec.Ingredients()){
                        matchList.add(qParams.contains(ing));
                    }
                    indicatorList.add(matchList);
                }

                ctx.attribute("filteredRecipes", filteredRecipes);
                ctx.attribute("indicatorList", indicatorList);

            }
            catch (DatabaseException e) {
                ctx.attribute("message", "fejl i søgning af database");
            }

        }
        ctx.render("/friskforslag/search.html");
    }

    private static void findRecipe(Context ctx, ConnectionPool connectionPool){
        System.out.println("FIND RECIPE : " + ctx.path());
        String reqRecipe = ctx.path().substring(ctx.path().lastIndexOf('/') + 1);

        // hardcoded string saves :)
        reqRecipe = reqRecipe.replace("%20", " ");
        reqRecipe = reqRecipe.replace("%C3%A6", "æ");

        System.out.println("REQUESTED RECIPE : " + reqRecipe);

        FriskForslagRecipe fetchedRecipe = null;
        try {
            fetchedRecipe = FriskForslagMapper.SelectRecipeByName(connectionPool, reqRecipe);
            ctx.attribute("fetchedRecipe", fetchedRecipe);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        System.out.println("REQUESTED RECIPE : " + fetchedRecipe);
        ctx.render("/friskforslag/recipe.html");
    }
}
