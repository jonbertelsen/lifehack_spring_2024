package app.controllers;

import app.persistence.ConnectionPool;
import app.persistence.GalgeSpil;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.ArrayList;

public class HangmanController
{
    public static void addRoutes(Javalin app, ConnectionPool connectionPool, GalgeSpil galgeSpil)
    {
        app.get("/hangman", ctx -> index(ctx, connectionPool));
        app.post("/hangman/stage0", ctx -> startgame(ctx, galgeSpil, connectionPool));
        app.post("/hangman/guessletter", ctx -> guessLetter(ctx, galgeSpil, connectionPool));
        app.post("/hangman/guessword", ctx -> guessWord(ctx, galgeSpil, connectionPool));
    }

    private static void startgame(Context ctx, GalgeSpil galgeSpil, ConnectionPool connectionPool)
    {
        galgeSpil.resetStage();
        galgeSpil.resetAnswerList();
        galgeSpil.pickWord();
        galgeSpil.initializeShownWord();
        ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
        ctx.attribute("shownWord", galgeSpil.getShownWord());
        ctx.render("/hangman/stage0");
    }

    private static void guessLetter(Context ctx, GalgeSpil galgeSpil, ConnectionPool connectionPool)
    {
        //Hent form parameter
        String letter = ctx.formParam("letter");

        try
        {
            char character = letter.charAt(0);


            if (galgeSpil.alreadyInAnswerList(letter))
            {
                ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
                ctx.attribute("shownWord", galgeSpil.getShownWord());
                ArrayList<String> answers = galgeSpil.getAnswerList();
                ctx.attribute("answers", answers);
                ctx.attribute("message", "Du må kun gætte på det samme bogstav en gang!");
                stageSwitch(ctx, galgeSpil.getStageCount());
            } else if (Character.isLetter(character))
            {
                boolean correctLetter = galgeSpil.guessLetter(letter);
                ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
                ctx.attribute("shownWord", galgeSpil.getShownWord());
                ArrayList<String> answers = galgeSpil.getAnswerList();
                ctx.attribute("answers", answers);
                stageRender(ctx, correctLetter, galgeSpil);

            } else if (Character.isDigit(character))
            {
                ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
                ctx.attribute("shownWord", galgeSpil.getShownWord());
                ArrayList<String> answers = galgeSpil.getAnswerList();
                ctx.attribute("answers", answers);
                ctx.attribute("message", "Du må kun gætte på bogstaver! Ikke tal!");
                stageSwitch(ctx, galgeSpil.getStageCount());

            } else
            {
                ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
                ctx.attribute("shownWord", galgeSpil.getShownWord());
                ArrayList<String> answers = galgeSpil.getAnswerList();
                ctx.attribute("answers", answers);
                ctx.attribute("message", "Du må kun gætte på bogstaver! Ikke mærkelige tegn!");
                stageSwitch(ctx, galgeSpil.getStageCount());
            }

        }
        catch (StringIndexOutOfBoundsException e)
        {
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String> answers = galgeSpil.getAnswerList();
            ctx.attribute("answers", answers);
            ctx.attribute("message", "Du skal huske at skrive noget!");
            stageSwitch(ctx, galgeSpil.getStageCount());
        }

    }

    private static void guessWord(Context ctx, GalgeSpil galgeSpil, ConnectionPool connectionPool)
    {
        //Hent form parameter
        String word = ctx.formParam("word");

        if (word.equals(""))
        {
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String> answers = galgeSpil.getAnswerList();
            ctx.attribute("answers", answers);
            ctx.attribute("message", "Du skal huske at skrive noget!");
            stageSwitch(ctx, galgeSpil.getStageCount());
        } else if (galgeSpil.alreadyInAnswerList(word))
        {
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String> answers = galgeSpil.getAnswerList();
            ctx.attribute("answers", answers);
            ctx.attribute("message", "Du må kun gætte på det samme ord en gang!");
            stageSwitch(ctx, galgeSpil.getStageCount());
        } else if (galgeSpil.wordContainsOnlyLetters(word))
        {
            boolean correctWord = galgeSpil.guessWord(word);
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String> answers = galgeSpil.getAnswerList();
            ctx.attribute("answers", answers);
            stageRender(ctx, correctWord, galgeSpil);

        } else if (galgeSpil.wordContainsNumber(word))
        {
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String> answers = galgeSpil.getAnswerList();
            ctx.attribute("answers", answers);
            ctx.attribute("message", "Dit ord må ikke indeholde tal!");
            stageSwitch(ctx, galgeSpil.getStageCount());

        } else
        {
            ctx.attribute("correctAnswer", galgeSpil.getCorrectAnswer());
            ctx.attribute("shownWord", galgeSpil.getShownWord());
            ArrayList<String> answers = galgeSpil.getAnswerList();
            ctx.attribute("answers", answers);
            ctx.attribute("message", "Dit ord må ikke indeholde mærkelige tegn!");
            stageSwitch(ctx, galgeSpil.getStageCount());
        }
    }

    private static void index(Context ctx, ConnectionPool connectionPool)
    {
        ctx.render("/hangman/index.html");
    }

    private static void stageRender(Context ctx, boolean correctLetter, GalgeSpil galgeSpil)
    {
        if (correctLetter && galgeSpil.getShownWord().equals(galgeSpil.getCorrectAnswer()))
        {
            stageSwitch(ctx, 11);

        } else if (correctLetter)
        {
            stageSwitch(ctx, galgeSpil.getStageCount());
        } else if (!correctLetter)
        {
            stageSwitch(ctx, galgeSpil.getStageCount() + 1);
            galgeSpil.nextStage();
        }

    }

    private static void stageSwitch(Context ctx, int stageCount)
    {
        switch (stageCount)
        {
            case 0:
                ctx.render("hangman/stage0.html");
                break;
            case 1:
                ctx.render("hangman/stage1.html");
                break;
            case 2:
                ctx.render("hangman/stage2.html");
                break;
            case 3:
                ctx.render("hangman/stage3.html");
                break;
            case 4:
                ctx.render("hangman/stage4.html");
                break;
            case 5:
                ctx.render("hangman/stage5.html");
                break;
            case 6:
                ctx.render("hangman/stage6.html");
                break;
            case 7:
                ctx.render("hangman/stage7.html");
                break;
            case 8:
                ctx.render("hangman/stage8.html");
                break;
            case 9:
                ctx.render("hangman/stage9.html");
                break;
            case 10:
                ctx.render("hangman/gameover.html");
                break;
            case 11:
                ctx.render("hangman/victoryscreen.html");
                break;
            default:
                break;
        }
    }

}