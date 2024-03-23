package app.persistence;

import app.entities.WardrobeCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WardrobeCategoryMapper {

    public static Map<Integer,WardrobeCategory> getAllCategories(ConnectionPool connectionPool){

        String sql = "SELECT * from wardrobe_category";

        Map<Integer,WardrobeCategory> categoryMap = new HashMap<>();

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int categoryId = rs.getInt("category_id");
                String name = rs.getString("name");

                WardrobeCategory category = new WardrobeCategory(categoryId,name);

                categoryMap.put(categoryId,category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryMap;
    }
}
