package app.persistence;

import app.entities.WardrobeItem;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardrobeItemMapper {
    public static List<WardrobeItem> getAllItems(ConnectionPool connectionPool){

        List<WardrobeItem> itemList = new ArrayList<>();

        String sql = "SELECT * from wardrobe_item";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int itemId = rs.getInt("item_id");
                String brand = rs.getString("brand");
                String color = rs.getString("color");
                int price = rs.getInt("price");
                String size = rs.getString("size");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category-id");
                int userId = rs.getInt("users_id");

                WardrobeItem item = new WardrobeItem(itemId,brand,color,price,size,description,categoryId,userId);
                itemList.add(item);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }
    public static void createItem(String brand, String description, int price, int categoryId, String color, String size,  ConnectionPool connectionPool){

        String sql = "insert Into item (brand,description,price,catergory_id,color,size) VALUES (?,?,?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {

            ps.setString(1,brand);
            ps.setString(2,description);
            ps.setInt(3,price);
            ps.setInt(4,categoryId);
            ps.setString(5,color);
            ps.setString(6,size);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteItem(int itemId, ConnectionPool connectionPool){

        String sql = "DELETE FROM item WHERE item_id = (?)";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1,itemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
