package app.persistence;

import java.util.ArrayList;
import java.util.Random;

public class GalgeSpil {

    private ArrayList<String> answerList = new ArrayList<>();

    private int stageCount = 0;

    private String correctAnswer;
    private String shownWord;
    private Random ran = new Random();
    private ArrayList<String> words = new ArrayList<>();


    public void initializeWords() {
        words.add("ANANAS");
        words.add("BANAN");
        words.add("KAGE");
        words.add("BIL");
        words.add("HUND");
        words.add("KAT");
        words.add("ÆBLE");
        words.add("BROCCOLI");
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
    public void initializeShownWord(){
        shownWord="";
        for (int i = 0; i < correctAnswer.length(); i++) {
            shownWord+="-";
        }
    }

    private void revealLetter(String letter){
        for (int i = 0; i < shownWord.length(); i++) {
            if(correctAnswer.charAt(i)==letter.toUpperCase().charAt(0)){
                shownWord = replaceCharBasedOnIndex(shownWord, letter.toUpperCase().charAt(0), i);
            }
        }
    }
    private String replaceCharBasedOnIndex(String word, char letter, int index){
        String newWord="";

        for (int i = 0; i < index; i++) {
            newWord+= word.charAt(i);
        }
        newWord+=letter;

        if(index<(word.length()-1)){
            for (int i = index+1; i < word.length(); i++) {
                newWord+= word.charAt(i);
            }
        }

        return newWord;
    }

    public boolean guessLetter(String letter){
        if(correctAnswer.contains(letter.toUpperCase())) {
            revealLetter(letter);
            answerList.add(letter.toUpperCase());
            return true;
        } else{
            answerList.add(letter.toUpperCase());
            return false;
        }
    }

    public boolean guessWord(String word){
        if(correctAnswer.equals(word.toUpperCase())) {
            shownWord=correctAnswer;
            answerList.add(word.toUpperCase());
            return true;
        } else{
            answerList.add(word.toUpperCase());
            return false;
        }
    }

    public boolean alreadyInAnswerList(String letter) {
        if (answerList.contains(letter.toUpperCase())) {
            return true;
        }
        return false;
    }
    public void nextStage(){
            stageCount++;
    }

    public void resetStage(){
    stageCount=0;
    }

    public void resetAnswerList(){
        ArrayList<String> emptyList = new ArrayList<>();
        answerList = emptyList;
    }
    public boolean wordContainsNumber(String word) {
        boolean containsNumber = false;
        for (char c : word.toCharArray()) {
            if (Character.isDigit(c)) {
                containsNumber = true;
            }
        }
        return containsNumber;
    }
    public boolean wordContainsOnlyLetters(String word) {
        boolean containsOnlyLetters = true;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
            }else{
                containsOnlyLetters = false;
            }
        }
        return containsOnlyLetters;
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

    public ArrayList<String> getAnswerList() {
        return answerList;
    }



}

