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
        app.get("/wardroberedit", ctx -> WardrobeCategoryController.viewCategory(ctx,connectionPool));
    }

    private static void viewWardrobe(Context ctx, ConnectionPool connectionPool) {
        User user = ctx.sessionAttribute("currentUser");
        List<WardrobeItem> wardrobeItemList = WardrobeItemMapper.getAllItemsPerUser(user.getUserId(), connectionPool);
        Map<Integer, WardrobeCategory> wardrobeCategoryMap = WardrobeCategoryMapper.getAllCategories(connectionPool);
        ctx.sessionAttribute("categoryMap", wardrobeCategoryMap);
        ctx.attribute("itemList", wardrobeItemList);
        ctx.render("wardrober/index.html");
    }

    private static void deleteItem(Context ctx, ConnectionPool connectionPool) {

        User user = ctx.sessionAttribute("currentUser");

        try {
            int itemId = Integer.parseInt(ctx.formParam("itemId"));
            WardrobeItemMapper.delete(itemId,connectionPool);
            List<WardrobeItem> itemList = WardrobeItemMapper.getAllItemsPerUser(user.getUserId(),connectionPool);
            ctx.sessionAttribute("itemList",itemList);
            // TODO: Change the above to maybe just attribute
            ctx.render("wardrober/index.html");


        } catch (NumberFormatException e){

            ctx.attribute("message", e.getMessage());
            ctx.render("wardrober/index.html");

        }
    }

    public static void editItem(Context ctx, ConnectionPool connectionPool ){

        User user = ctx.sessionAttribute("currentUser");

        try {
            //trækker ud af web-formularen
            String brand = ctx.formParam("brand");
            String description = ctx.formParam("description");
            String color = ctx.formParam("color");
            String size = ctx.formParam("size");
            int price = Integer.parseInt(ctx.formParam("price"));
            int categoryId = Integer.parseInt(ctx.formParam("category"));

            WardrobeItemMapper.createItem(brand,description,price,categoryId,color,size,connectionPool);

            List<WardrobeItem> itemList = WardrobeItemMapper.getAllItemsPerUser(user.getUserId(),connectionPool);

            ctx.attribute("itemList",itemList);
            //rendering: fletter html sammen med data, fortolker data
            ctx.render("wardrober/index.html");
        } catch (NumberFormatException e){

            ctx.attribute("message", e.getMessage());
            ctx.render("wardrober/index.html");

        }


    }
}