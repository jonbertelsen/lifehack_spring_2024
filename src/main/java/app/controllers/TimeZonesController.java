package app.controllers;

import app.entities.CountryCode;
import app.persistence.ConnectionPool;
import app.persistence.TimezoneMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;
import java.util.Map;

public class TimeZonesController {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool) {
        app.get("/timezones", ctx -> index(ctx, connectionPool));
        app.post("/calculate-timezone-difference", ctx -> calculateTimezoneDifference(ctx, connectionPool));
    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
        List<CountryCode> countryCodeList = ctx.sessionAttribute("countryCodeList");
        if (countryCodeList == null)
        {
            // Henter landekoder fra databasen
            countryCodeList = TimezoneMapper.getCountryCodes(connectionPool);
            ctx.sessionAttribute("countryCodeList", countryCodeList);
        }
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
            // Sender brugeren til resultatet siden
            ctx.attribute("difference", timeDifference);
            ctx.render("/timezones/result.html");
        } catch (RuntimeException e) {
            ctx.status(500).result("An error occurred: " + e.getMessage());
        }
    }
}