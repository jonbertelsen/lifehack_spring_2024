package app.entities;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */
public class Haiku {
    private String haikuParts;
    private boolean isFiveSyllables;

    public Haiku(String haikuParts, boolean isFiveSyllables) {
        this.haikuParts = haikuParts;
        this.isFiveSyllables = isFiveSyllables;
    }

    public String getHaikuPart() {
        return haikuParts;
    }

    public boolean getIsFiveSyllables() {
        return isFiveSyllables;
    }

}


