package app.entities;

public class WardrobeItem {

    private int itemId;
    private String brand;
    private String color;
    private int price;
    private String size;

    private String description;
    private int categoryId;
    private int usersId;


    public WardrobeItem(int itemId, String brand, String color, int price, String size,String description, int categoryId, int usersId) {
        this.itemId = itemId;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.size = size;
        this.description = description;
        this.categoryId = categoryId;
        this.usersId = usersId;
    }

    public WardrobeItem(String brand, String color, int price, String size, String description, int categoryId, int usersId)
    {
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.size = size;
        this.description = description;
        this.categoryId = categoryId;
        this.usersId = usersId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getUsersId() {
        return usersId;
    }

    @Override
    public String toString() {
        return "WardrobeItem{" +
                "itemId=" + itemId +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", usersId=" + usersId +
                '}';
    }
}
