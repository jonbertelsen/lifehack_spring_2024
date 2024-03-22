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
        initDB(connectionPool, 500);
    }

    /**
     * initDB, adds recipes to the database if minRecsInDB is greater
     * than what is currently in the datase.
     * @param connectionPool
     * @param minRecsInDB keep this low to avoid a very long loading time, recommended is 100-500
     * (max is 5000)
     */
    private static void initDB(ConnectionPool connectionPool, int minRecsInDB)
    {
        if (minRecsInDB > 5000)
            minRecsInDB = 5000;
        try {
            FriskForslagMapper.ResetIdSequence(connectionPool);
            int dbSize = FriskForslagMapper.GetNumOfRecipesInDatabase(connectionPool);
            if (dbSize < minRecsInDB) {
                System.err.printf("%sFriskForslag info:%s\t>>> database size (%d) is less than requested %d... %s\t>>> adding %d new recipes (this might take some time, consider lowering the parameter if you are impatient)%n",
                        System.lineSeparator().repeat(2),
                        System.lineSeparator().repeat(1),
                        dbSize, minRecsInDB,
                        System.lineSeparator(),
                        minRecsInDB - dbSize);
                FriskForslagWebScraper.ScrapeRecipesFromOpskrifterDK(connectionPool, minRecsInDB);
                System.err.println("\t>>> done inserting new recipes ...");
            } else {
                System.err.printf("%sFriskForslag info:%s\t>>> Did not scrape recipes -- number of recipes stored exceed the minimum requested\t(requested: %d | database size: %d)%n",
                        System.lineSeparator().repeat(2),
                        System.lineSeparator().repeat(1),
                        minRecsInDB, dbSize);
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
