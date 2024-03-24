package app.controllers;

import app.entities.User;
import app.entities.WardrobeCategory;
import app.entities.WardrobeItem;
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
        app.post("/deleteitem", ctx -> deleteItem(ctx, connectionPool));
        app.post("/edititem", ctx -> editItem(ctx, connectionPool));
        app.post("/updateitem", ctx -> updateItem(ctx, connectionPool));
    }

    private static void updateItem(Context ctx, ConnectionPool connectionPool)
    {
        // TODO: Implement this method
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

    public static void editItem(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");
        List<WardrobeItem> itemList = ctx.sessionAttribute("itemList");

        try {
            int itemId = Integer.parseInt(ctx.formParam("itemId"));
            WardrobeItem currentItem = null;
            for (WardrobeItem i : itemList) {
                if (i.getItemId() == itemId) {
                    currentItem = i;
                }
            }
            String brand = currentItem.getBrand();
            String description = currentItem.getDescription();
            String color = currentItem.getColor();
            String size = currentItem.getSize();
            int price = currentItem.getPrice();
            int categoryId = currentItem.getCategoryId();

            ctx.sessionAttribute("item", currentItem);

            //rendering: fletter html sammen med data, fortolker data
            ctx.render("wardrober/edititem.html");

        } catch (NumberFormatException e) {

            ctx.attribute("message", e.getMessage());
            ctx.render("wardrober/index.html");

        }
    }
}