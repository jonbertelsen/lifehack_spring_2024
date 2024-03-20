package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.GalgeSpil;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;

public class A07Controller {
    public static void addRoutes(Javalin app, ConnectionPool connectionPool, GalgeSpil galgeSpil) {
        app.get("/A07", ctx -> index(ctx, connectionPool));
        app.post("/A07/stage0", ctx -> startgame(ctx, galgeSpil, connectionPool));
        app.post("/A07/guessletter", ctx -> guessLetter(ctx, galgeSpil, connectionPool));
    }

    private static void startgame(Context ctx, GalgeSpil galgeSpil, ConnectionPool connectionPool) {
        galgeSpil.resetStage();
        galgeSpil.pickWord();
        galgeSpil.initializeShownWord();
        ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
        ctx.attribute("shownWord", galgeSpil.getShownWord());
        ctx.render("/A07/stage0");
    }



    private static void guessLetter(Context ctx, GalgeSpil galgeSpil, ConnectionPool connectionPool) {
        //Hent form parameter
        String letter = ctx.formParam("letter");

        char character = letter.charAt(0);
        if(galgeSpil.alreadyInAnswerList(letter)){
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String>answers=galgeSpil.getAnswerList();
            ctx.attribute("answers",answers);
            ctx.attribute("message", "Du må kun gætte på det samme bogstav en gang!");
            stageSwitch(ctx, galgeSpil.getStageCount());
        }
        else if (Character.isLetter(character)) {
            boolean correctLetter = galgeSpil.guessLetter(letter);
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String>answers=galgeSpil.getAnswerList();
            ctx.attribute("answers",answers);
            stageRender(ctx, correctLetter, galgeSpil);

        } else if (Character.isDigit(character)) {
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String>answers=galgeSpil.getAnswerList();
            ctx.attribute("answers",answers);
            ctx.attribute("message", "Du må kun gætte på bogstaver! Ikke tal!");
            stageSwitch(ctx, galgeSpil.getStageCount());

        }else if(galgeSpil.alreadyInAnswerList(letter)){
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String>answers=galgeSpil.getAnswerList();
            ctx.attribute("answers",answers);
            ctx.attribute("message", "Du må kun gætte på det samme bogstav en gang!");
            stageSwitch(ctx, galgeSpil.getStageCount());
        }
        else {
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String>answers=galgeSpil.getAnswerList();
            ctx.attribute("answers",answers);
            ctx.attribute("message", "Du må kun gætte på bogstaver! Ikke mærkelige tegn!");
            stageSwitch(ctx, galgeSpil.getStageCount());
        }


    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
        ctx.render("/A07/index.html");
    }

    private static void stageRender(Context ctx, boolean correctLetter, GalgeSpil galgeSpil) {
        if (correctLetter && galgeSpil.getShownWord().equals(galgeSpil.getCorrectAnswer())) {
            stageSwitch(ctx, 11);

        } else if (correctLetter) {
            stageSwitch(ctx, galgeSpil.getStageCount());
        } else if (!correctLetter) {
            stageSwitch(ctx, galgeSpil.getStageCount()+1);
            galgeSpil.nextStage();
        }

    }

    private static void stageSwitch(Context ctx, int stageCount) {
        switch (stageCount) {
            case 0:
                ctx.render("A07/stage0.html");
                break;
            case 1:
                ctx.render("A07/stage1.html");
                break;
            case 2:
                ctx.render("A07/stage2.html");
                break;
            case 3:
                ctx.render("A07/stage3.html");
                break;
            case 4:
                ctx.render("A07/stage4.html");
                break;
            case 5:
                ctx.render("A07/stage5.html");
                break;
            case 6:
                ctx.render("A07/stage6.html");
                break;
            case 7:
                ctx.render("A07/stage7.html");
                break;
            case 8:
                ctx.render("A07/stage8.html");
                break;
            case 9:
                ctx.render("A07/stage9.html");
                break;
            case 10:
                ctx.render("A07/gameover.html");
                break;
            case 11:
                ctx.render("A07/victoryscreen.html");
                break;
            default:
                break;

        }
    }


}