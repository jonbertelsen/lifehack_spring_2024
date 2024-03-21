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

public class WardrobeCategoryController {

    public static void viewCategory(Context ctx, ConnectionPool connectionPool) {
        Map<Integer, WardrobeCategory> wardrobeCategoryMap = WardrobeCategoryMapper.getAllCategories(connectionPool);
        ctx.sessionAttribute("categoryMap", wardrobeCategoryMap);
        ctx.render("wardrober/index.html");
    }
}
