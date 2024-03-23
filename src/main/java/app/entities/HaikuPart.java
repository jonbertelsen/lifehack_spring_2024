package app.entities;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */
public class HaikuPart {
    private final int partId;
    private final String text;

    public HaikuPart(int partId, String text) {
        this.partId = partId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getPartId() {
        return partId;
    }
}
