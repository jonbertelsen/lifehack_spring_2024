package app.controllers;

import app.entities.FriskForslagRecipe;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.FriskForslagMapper;
import app.persistence.FriskForslagWebScraper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;
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
        // TODO: change this variable int to increase or decrease number of recipes in database
        initDB(connectionPool, 200);
    }

    private static void initDB(ConnectionPool connectionPool, int minWantedRecipesInDatabase)
    {
        try {
            int dbSize = FriskForslagMapper.GetNumOfRecipesInDatabase(connectionPool);
            if (dbSize < minWantedRecipesInDatabase) {
                FriskForslagWebScraper.crawlRecipeListsOpskrifterDK(connectionPool, minWantedRecipesInDatabase / 10);
            } else {
                System.err.println(System.lineSeparator().repeat(2)
                        + "\t>>>"
                        + "Did not scrape recipes -- "
                        + "number of recipes stored exceed the minimum requested\t(Requested: "
                        + minWantedRecipesInDatabase
                        + " / Actual: "
                        + dbSize
                        + ")");
            }
        } catch (DatabaseException e) {
            System.err.print("");
        }
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
                for (FriskForslagRecipe rec : filteredRecipes) {
                    List<Boolean> matchList = new ArrayList<>();
                    Boolean matched;
                    String[] qParamsSplit = qParams.split(" ");
                    for (String ing : rec.Ingredients()) {
                        matched = false;
                        for (String qp : qParamsSplit) {
                            if (ing.toLowerCase().contains(qp.toLowerCase())) {
                                matched = true;
                                break;
                            }
                        }
                        matchList.add(matched);
                    }
                    indicatorList.add(matchList);
                }

                ctx.attribute("filteredRecipes", filteredRecipes);
                ctx.attribute("indicatorList", indicatorList);

            } catch (DatabaseException e) {
                ctx.attribute("message", "fejl i søgning af database");
            }

        }
        ctx.render("/friskforslag/search.html");
    }

    private static void findRecipe(Context ctx, ConnectionPool connectionPool)
    {
        String reqRecipe = ctx.path().substring(ctx.path().lastIndexOf('/') + 1);

        // TODO: hardcoded string saves :)
        reqRecipe = reqRecipe.replace("%20", " ");
        reqRecipe = reqRecipe.replace("%C3%A6", "æ");
        reqRecipe = reqRecipe.replace("%C3%B8", "ø");
        reqRecipe = reqRecipe.replace("%C3%A5", "å");

        FriskForslagRecipe fetchedRecipe = null;
        try {
            fetchedRecipe = FriskForslagMapper.SelectRecipeByName(connectionPool, reqRecipe);
            ctx.attribute("fetchedRecipe", fetchedRecipe);
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }
        ctx.render("/friskforslag/recipe.html");
    }
}
