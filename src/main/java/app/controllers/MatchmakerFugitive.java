package app.entities;

/**

 Purpose:*
 @author: Jeppe Koch
 */
public class MatchmakerFugitive {
    private int fugitivesId;
    private String category;
    private String name;
    private String hairColor;
    private String eyeColor;
    private String sex;
    private String race;
    private String occupation;
    private String description;
    private String url;

    public MatchmakerFugitive(int fugitivesId, String url) {
        this.fugitivesId = fugitivesId;
        this.url = url;
    }

    public int getFugitivesId() {
        return fugitivesId;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getSex() {
        return sex;
    }

    public String getRace() {
        return race;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "MatchmakerFugitive{" +
                "fugitivesId=" + fugitivesId +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", sex='" + sex + '\'' +
                ", race='" + race + '\'' +
                ", occupation='" + occupation + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}