package app.controllers;

import app.entities.WardrobeCategory;
import app.entities.WardrobeItem;
import app.persistence.ConnectionPool;
import app.persistence.WardrobeCategoryMapper;
import app.persistence.WardrobeItemMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class WardrobeItemController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/wardrober", ctx -> ctx.render("wardrober/index.html"));
        app.post("deleteitem", ctx -> removeItem(ctx, connectionPool));
        app.post("edititem", ctx -> updateItem(ctx, connectionPool));
    }

    private static void removeItem(Context ctx, ConnectionPool connectionPool) {

        int itemId = Integer.parseInt(ctx.formParam("item_id"));

        WardrobeItemMapper.deleteItem(itemId,connectionPool);

        List<WardrobeItem> itemList = WardrobeItemMapper.getAllItems(connectionPool);

        ctx.attribute("itemList",itemList);

        ctx.render("index.html");
    }

    public static void updateItem(Context ctx, ConnectionPool connectionPool ){

        //trækker ud af web-formularen
        String brand = ctx.formParam("brand");
        String description = ctx.formParam("description");
        String color = ctx.formParam("color");
        String size = ctx.formParam("size");
        int price = Integer.parseInt(ctx.formParam("price"));
        int categoryId = Integer.parseInt(ctx.formParam("category"));

        WardrobeItemMapper.createItem(brand,description,price,categoryId,color,size,connectionPool);

        List<WardrobeItem> itemList = WardrobeItemMapper.getAllItems(connectionPool);

        ctx.attribute("itemList",itemList);
        //rendering: fletter html sammen med data, fortolker data
        ctx.render("index.html");
    }
}