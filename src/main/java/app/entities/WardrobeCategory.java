package app.entities;

public class WardrobeCategory {

    private int categoryId;
    private String name;

    public WardrobeCategory(int categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "WardrobeCategory{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
