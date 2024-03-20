package app.controllers;

import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.GalgeSpil;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

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
        ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
        ctx.attribute("shownWord", galgeSpil.getShownWord());
        stageRender(ctx, galgeSpil.guessLetter(letter), galgeSpil);
        galgeSpil.nextStage(letter);

    }

    private static void index(Context ctx, ConnectionPool connectionPool) {
        ctx.render("/A07/index.html");
    }

    private static void stageRender(Context ctx, boolean correctLetter, GalgeSpil galgeSpil) {
        if (correctLetter) {
            stageSwitch(ctx, galgeSpil.getStageCount());
        } else if (!correctLetter) {
            stageSwitch(ctx, galgeSpil.getStageCount()+1);
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
            default:
                break;

        }
    }


}