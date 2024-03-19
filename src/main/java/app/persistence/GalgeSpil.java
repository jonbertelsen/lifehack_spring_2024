package app.persistence;

import java.util.ArrayList;
import java.util.Random;

public class GalgeSpil {

    private int stageCount = 0;

    private String correctAnswer;
    private String shownWord;
    private Random ran = new Random();
    private ArrayList<String> words = new ArrayList<>();

    private ArrayList<String> guessedLetters = new ArrayList<>();

    public GalgeSpil(){

    }

    public void initializeWords() {
        words.add("ANANAS");
        words.add("BANAN");
        words.add("KAGE");
        words.add("BIL");
        words.add("HUND");
        words.add("KAT");
        words.add("ÆBLE");
        words.add("BROKOLI");
        words.add("FODBOLD");
        words.add("STOL");
        words.add("SKRIVEBORD");
        words.add("BLYANT");
        words.add("MUS");
        words.add("TASTATUR");
        words.add("BORD");
        words.add("BÆRBAR");
        words.add("FLASKE");
        words.add("VAND");
        words.add("SKOLE");
        words.add("BIOGRAF");
    }

    public String pickWord(){
        initializeWords();
        correctAnswer = words.get(ran.nextInt(words.size()));
        return correctAnswer;
    }

    public boolean guessLetter(String letter){
        if(correctAnswer.contains(letter.toUpperCase())) {
            return true;
        } else{
            return false;
        }
    }
    public void nextStage(String letter){
        if (!guessLetter(letter))
        {
            stageCount++;
        }
    }
    public void resetStage(){
    stageCount=0;
    }


    public int getStageCount() {
        return stageCount;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getShownWord() {
        return shownWord;
    }

    public ArrayList<String> getWords() {
        return words;
    }


}

