package app.controllers;

import app.entities.User;
import app.entities.WardrobeCategory;
import app.entities.WardrobeItem;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.WardrobeCategoryMapper;
import app.persistence.WardrobeItemMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class WardrobeItemController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/wardrober", ctx -> viewWardrobe(ctx, connectionPool));
        app.post("/wardrober/deleteitem", ctx -> deleteItem(ctx, connectionPool));
        app.post("/wardrober/edititem", ctx -> editItem(ctx, connectionPool));
        app.post("/wardrober/updateitem", ctx -> updateItem(ctx, connectionPool));
        app.post("/wardrober/createitem", ctx -> createItem(ctx, connectionPool));
    }

    private static void viewWardrobe(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");
        if (user != null) {
            List<WardrobeItem> wardrobeItemList = WardrobeItemMapper.getAllItemsPerUser(user.getUserId(), connectionPool);
            Map<Integer, WardrobeCategory> wardrobeCategoryMap = WardrobeCategoryMapper.getAllCategories(connectionPool);
            ctx.sessionAttribute("categoryMap", wardrobeCategoryMap);
            ctx.sessionAttribute("itemList", wardrobeItemList);
            ctx.render("wardrober/index.html");
        } else {
            ctx.attribute("message", "You need to be logged in to view your wardrobe");
            ctx.render("/index.html");
        }
    }

    private static void deleteItem(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");

        try {
            int itemId = Integer.parseInt(ctx.formParam("itemId"));
            WardrobeItemMapper.delete(itemId, connectionPool);
            List<WardrobeItem> itemList = WardrobeItemMapper.getAllItemsPerUser(user.getUserId(), connectionPool);
            ctx.sessionAttribute("itemList", itemList);
            ctx.render("wardrober/index.html");
        } catch (NumberFormatException e) {

            ctx.attribute("message", e.getMessage());
            ctx.render("wardrober/index.html");
        }
    }

    private static void editItem(Context ctx, ConnectionPool connectionPool)
    {
        User user = ctx.sessionAttribute("currentUser");

        int itemId = Integer.parseInt(ctx.formParam("itemId"));
        try
        {
            WardrobeItem wardrobeItem = WardrobeItemMapper.getItemById(itemId, connectionPool);
            ctx.sessionAttribute("item", wardrobeItem);
            ctx.render("wardrober/edititem.html");
        }
        catch (DatabaseException e)
        {
            ctx.attribute("message", e.getMessage());
            ctx.render("wardrober/index.html");
        }
    }

    private static void updateItem(Context ctx, ConnectionPool connectionPool)
    {
        User user = ctx.sessionAttribute("currentUser");

        try {
            int itemId = Integer.parseInt(ctx.formParam("itemId"));

            String brand = ctx.formParam("brand");
            String description = ctx.formParam("description");
            String color = ctx.formParam("color");
            String size = ctx.formParam("size");
            int price = Integer.parseInt(ctx.formParam("price"));
            int categoryId = Integer.parseInt(ctx.formParam("categoryid"));

            // TODO: update database with new item information
            WardrobeItem updatedItem = new WardrobeItem(itemId, brand, color, price, size, description, categoryId, user.getUserId());
            WardrobeItemMapper.updateItem(updatedItem, connectionPool);

            List<WardrobeItem> wardrobeItemList = WardrobeItemMapper.getAllItemsPerUser(user.getUserId(), connectionPool);
            ctx.sessionAttribute("itemList", wardrobeItemList);
            ctx.render("wardrober/index.html");

        } catch (Exception e) {

            ctx.attribute("message", e.getMessage());
            ctx.render("wardrober/index.html");

        }
    }

    private static void createItem(Context ctx, ConnectionPool connectionPool)
    {
        User user = ctx.sessionAttribute("currentUser");

        String brand = ctx.formParam("brand");
        String description = ctx.formParam("description");
        String color = ctx.formParam("color");
        String size = ctx.formParam("size");
        int price = Integer.parseInt(ctx.formParam("price"));
        int categoryId = Integer.parseInt(ctx.formParam("categoryid"));

        WardrobeItem newItem = new WardrobeItem(brand, color, price, size, description, categoryId, user.getUserId());
        try
        {
            WardrobeItemMapper.createItem(newItem, connectionPool);
        }
        catch (DatabaseException e)
        {
            ctx.attribute("message", "Du mangler at indtaste nogle oplysninger");
            ctx.render("wardrober/index.html");
        }

        List<WardrobeItem> wardrobeItemList = WardrobeItemMapper.getAllItemsPerUser(user.getUserId(), connectionPool);
        ctx.sessionAttribute("itemList", wardrobeItemList);
        ctx.render("wardrober/index.html");
    }
}