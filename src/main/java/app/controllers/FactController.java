package app.controllers;

import app.entities.Fact;
import app.persistence.ConnectionPool;
import app.persistence.FactMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FactController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/funfacts", ctx -> index(ctx, connectionPool));
        app.post("/getfact", ctx -> getfact(ctx, connectionPool));
    }

    private static void getfact(Context ctx, ConnectionPool connectionPool) {
        Map<String, Object> factMap = new HashMap<>();
        try {
            String dateStr = ctx.formParam("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMdd");
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Formoder det oprindelige format
            String formattedDate = date.format(formatter);

            Fact fact = FactMapper.getFactByDate(formattedDate, connectionPool);

            if (fact != null) {
                factMap.put("fact", fact.getFact());
                factMap.put("date", date.format(DateTimeFormatter.ofPattern("dd. MMMM yyyy")));
            } else {
                factMap.put("fact", "Ingen facts tilgængelige for denne dato.");
                factMap.put("date", date.format(DateTimeFormatter.ofPattern("dd. MMMM yyyy")));
            }

            ctx.render("funfacts/facts.html", factMap);
        } catch (NumberFormatException e) {
            ctx.attribute("message", e.getMessage());
            ctx.render("funfacts/index.html");
        }
    }





        private static void index (Context ctx, ConnectionPool connectionPool){
            ctx.render("funfacts/index.html");
        }
    }