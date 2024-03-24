package app.persistence;

import app.entities.WardrobeItem;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardrobeItemMapper {
    public static List<WardrobeItem> getAllItemsPerUser(int userId, ConnectionPool connectionPool) {

        List<WardrobeItem> itemList = new ArrayList<>();

        String sql = "SELECT * from wardrobe_item where users_id = (?) order by category_id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                String brand = rs.getString("brand");
                String color = rs.getString("color");
                int price = rs.getInt("price");
                String size = rs.getString("size");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                int id = rs.getInt("users_id");

                WardrobeItem item = new WardrobeItem(itemId, brand, color, price, size, description, categoryId, id);
                itemList.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemList;
    }

    public static void createItem(String brand, String description, int price, int categoryId, String color, String size, ConnectionPool connectionPool) {

        WardrobeItem newItem = null;

        String sql = "insert Into wardrobe_item (brand,description,price,catergory_id,color,size) VALUES (?,?,?,?,?,?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {

            ps.setString(1, brand);
            ps.setString(2, description);
            ps.setInt(3, price);
            ps.setInt(4, categoryId);
            ps.setString(5, color);
            ps.setString(6, size);
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int itemId, ConnectionPool connectionPool) {

        String sql = "DELETE FROM wardrobe_item WHERE item_id = (?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static WardrobeItem getItemById(int itemId, ConnectionPool connectionPool) throws DatabaseException {
        WardrobeItem item = null;

        String sql = "select * from wardrobe_item where item_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("item_id");
                String brand = rs.getString("brand");
                String color = rs.getString("color");
                int price = rs.getInt("price");
                String size = rs.getString("size");
                String description = rs.getString("description");
                int categoryId = rs.getInt("category_id");
                int userId = rs.getInt("users_id");

                item = new WardrobeItem(itemId, brand, color, price, size, description, categoryId, id);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl ved hentning af task med id" + item.getItemId());


        }
        return item;
    }
}

