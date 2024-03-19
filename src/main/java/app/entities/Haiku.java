package app.entities;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */
public class Haiku {
    private final String haikuParts;
    private final boolean isFiveSyllables;

    public Haiku(String haikuParts, boolean isFiveSyllables) {
        this.haikuParts = haikuParts;
        this.isFiveSyllables = isFiveSyllables;
    }

    public String getHaikuPart(){
        return haikuParts;
    }
    public boolean getIsFiveSyllables() {
        return isFiveSyllables;
    }


    /*
    public Haiku(String haikuParts, boolean isFiveSyllables) {
        this.haikuParts = haikuParts;
        this.isFiveSyllables = isFiveSyllables;
    }
     */
}
