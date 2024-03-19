package app.persistence;

import java.util.ArrayList;
import java.util.Random;

public class GalgeSpil {

    int stageCount = 0;

    String correctAnswer;
    String shownWord;
    Random ran = new Random();
    ArrayList<String> words = new ArrayList<>();

    ArrayList<String> guessedLetters = new ArrayList<>();

    public GalgeSpil(){

    }

    public String pickWord(){
        correctAnswer = words.get(ran.nextInt(words.size()));
        return correctAnswer;
    }

    public boolean guessLetter(String letter){
        if(correctAnswer.contains(letter)) {
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

    //sponsoreret af chatten:
    public void fillPickWord(){
        String ord1 = "æble";
        String ord2 = "banan";
        String ord3 = "gulerod";
        String ord4 = "hund";
        String ord5 = "elefant";
        String ord6 = "fisk";
        String ord7 = "giraf";
        String ord8 = "hus";
        String ord9 = "is";
        String ord10 = "jakke";
        String ord11 = "drage";
        String ord12 = "citron";
        String ord13 = "abe";
        String ord14 = "notesbog";
        String ord15 = "appelsin";
        String ord16 = "pære";
        String ord17 = "tæppe";
        String ord18 = "kanin";
        String ord19 = "slange";
        String ord20 = "træ";
        String ord21 = "paraply";
        String ord22 = "vase";
        String ord23 = "vandmelon";
        String ord24 = "xylofon";
        String ord25 = "yacht";
        String ord26 = "zebra";
        String ord27 = "fly";
        String ord28 = "bjørn";
        String ord29 = "kage";
        String ord30 = "drage";
        String ord31 = "elefant";
        String ord32 = "frø";
        String ord33 = "guitar";
        String ord34 = "hat";
        String ord35 = "ø";
        String ord36 = "jungle";
        String ord37 = "kænguru";
        String ord38 = "lampe";
        String ord39 = "måne";
        String ord40 = "ninja";
        String ord41 = "blæksprutte";
        String ord42 = "pingvin";
        String ord43 = "dronning";
        String ord44 = "raket";
        String ord45 = "snefnug";
        String ord46 = "tiger";
        String ord47 = "enhjørning";
        String ord48 = "vulkan";
        String ord49 = "troldmand";
        String ord50 = "xylofon";

        words.add(ord1);
        words.add(ord2);
        words.add(ord3);
        words.add(ord4);
        words.add(ord5);
        words.add(ord6);
        words.add(ord7);
        words.add(ord8);
        words.add(ord9);
        words.add(ord10);
        words.add(ord11);
        words.add(ord12);
        words.add(ord13);
        words.add(ord14);
        words.add(ord15);
        words.add(ord16);
        words.add(ord17);
        words.add(ord18);
        words.add(ord19);
        words.add(ord20);
        words.add(ord21);
        words.add(ord22);
        words.add(ord23);
        words.add(ord24);
        words.add(ord25);
        words.add(ord26);
        words.add(ord27);
        words.add(ord28);
        words.add(ord29);
        words.add(ord30);
        words.add(ord31);
        words.add(ord32);
        words.add(ord33);
        words.add(ord34);
        words.add(ord35);
        words.add(ord36);
        words.add(ord37);
        words.add(ord38);
        words.add(ord39);
        words.add(ord40);
        words.add(ord41);
        words.add(ord42);
        words.add(ord43);
        words.add(ord44);
        words.add(ord45);
        words.add(ord46);
        words.add(ord47);
        words.add(ord48);
        words.add(ord49);
        words.add(ord50);
    }
}

