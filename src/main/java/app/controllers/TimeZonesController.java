package app.controllers;

import app.persistence.ConnectionPool;
import app.persistence.TimezoneMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.Map;

public class TimeZonesController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/timezones", ctx -> index(ctx));
        app.post("/calculate-timezone-difference", ctx -> calculateTimezoneDifference(ctx, connectionPool));
    }

    private static void index(Context ctx) {
        ctx.render("/timezones/index.html");
    }

    private static void calculateTimezoneDifference(Context ctx, ConnectionPool connectionPool) {
        String fromCountryCode = ctx.formParam("from");
        String toCountryCode = ctx.formParam("to");
        try {
            int differenceInSeconds = TimezoneMapper.calculateTimeDifference(fromCountryCode, toCountryCode, connectionPool);
            // Konvertererer sekunder til timer og minutter
            int hours = differenceInSeconds / 3600;
            int minutes = (differenceInSeconds % 3600) / 60;
            String timeDifference = String.format("%d hours and %d minutes", hours, minutes);
            // Sender brugeren til resultatet sidern
            ctx.render("/timezones/result.html", Map.of("difference", timeDifference));
        } catch (RuntimeException e) {
            ctx.status(500).result("An error occurred: " + e.getMessage());
        }
    }
}