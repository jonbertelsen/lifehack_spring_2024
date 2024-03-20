package app.controllers;

import app.entities.User;
import app.persistence.ConnectionPool;
import app.persistence.HaikuMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Arrays;
import java.util.Random;

public class HaikuController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.get("/haiku", ctx -> index(ctx, connectionPool));
        app.post("/generatefirstlinenormal", ctx -> generateFirstLineNormal(ctx, connectionPool));
        app.post("/generatesecondlinenormal", ctx -> generateSecondLineNormal(ctx, connectionPool));
        app.post("/generatethirdlinenormal", ctx -> generateThirdLineNormal(ctx, connectionPool));
        app.post("/generatefirstlinebykevin", ctx -> generateFirstLineByKevin(ctx, connectionPool));
        app.post("/generatesecondlinebykevin", ctx -> generateSecondLineByKevin(ctx, connectionPool));
        app.post("/generatethirdlinebykevin", ctx -> generateThirdLineByKevin(ctx, connectionPool));
        app.post("/generatefirstlineaboutkevin", ctx -> generateFirstLineAboutKevin(ctx, connectionPool));
        app.post("/generatesecondlineaboutkevin", ctx -> generateSecondLineAboutKevin(ctx, connectionPool));
        app.post("/generatethirdlineaboutkevin", ctx -> generateThirdLineAboutKevin(ctx, connectionPool));
        app.post("/clearfields",ctx -> clearFields(ctx,connectionPool));
    }

    private static void clearFields(Context ctx, ConnectionPool connectionPool) {
        String clear = "";
        try {
            ctx.sessionAttribute("firstline", clear);
            ctx.sessionAttribute("secondline", clear);
            ctx.sessionAttribute("thirdline", clear);
            ctx.render("/haiku/index.html");

        } catch (Exception e) {
            // Handle exceptions
            ctx.attribute("message", "Something went wrong. Please try again.");
            ctx.render("/haiku/index.html");
        }

    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        User currentUser = ctx.sessionAttribute("currentUser");
        if (currentUser != null) {
            ctx.sessionAttribute("username",currentUser.getUserName());
            ctx.sessionAttribute("penname",shuffleString(currentUser.getUserName()));
        } else {
            System.out.println("Noone is logged in");
        }
        ctx.render("/haiku/index.html");

    }

    private static void generateFirstLineNormal(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = true;
        boolean madeByKevin = false;
        boolean aboutKevin = false;

        try {
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("firstline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (Exception e) {
            // Handle exceptions
            ctx.attribute("message", "Something went wrong. Please try again.");
            ctx.render("/haiku/index.html");
        }
    }

    private static void generateSecondLineNormal(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = false;
        boolean madeByKevin = false;
        boolean aboutKevin = false;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("secondline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.attribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    private static void generateThirdLineNormal(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = true;
        boolean madeByKevin = false;
        boolean aboutKevin = false;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("thirdline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.attribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    private static void generateFirstLineByKevin(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = true;
        boolean madeByKevin = true;
        boolean aboutKevin = false;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.attribute("firstline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.sessionAttribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    private static void generateSecondLineByKevin(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = false;
        boolean madeByKevin = true;
        boolean aboutKevin = false;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("secondline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.attribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    private static void generateThirdLineByKevin(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = true;
        boolean madeByKevin = true;
        boolean aboutKevin = false;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("thirdline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.attribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    private static void generateFirstLineAboutKevin(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = true;
        boolean madeByKevin = false;
        boolean aboutKevin = true;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("firstline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.attribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    private static void generateSecondLineAboutKevin(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = false;
        boolean madeByKevin = false;
        boolean aboutKevin = true;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("secondline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.attribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    private static void generateThirdLineAboutKevin(Context ctx, ConnectionPool connectionPool) {
        boolean isFiveSyllables = true;
        boolean madeByKevin = false;
        boolean aboutKevin = true;

        try{
            String haikuText = HaikuMapper.generateHaikuPart(connectionPool,isFiveSyllables,madeByKevin,aboutKevin);
            ctx.sessionAttribute("thirdline", haikuText);
            ctx.render("/haiku/index.html");

        } catch (AbstractMethodError e){
            ctx.attribute("message","Noget gik galt prøv igen");
            ctx.render("templates/haiku/index.html");
        }
    }

    public static String shuffleString(String input) {
        char[] characters = input.toCharArray();
        Random rand = new Random();

        for (int i = characters.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            // Swap characters[i] and characters[j]
            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }

        return new String(characters);
    }
}