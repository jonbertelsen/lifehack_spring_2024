package app.persistence;

import app.entities.Fact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FactMapper {

    public static Fact getFactByDate(String dateStr, ConnectionPool connectionPool) {
        String sql = "SELECT * FROM funfact WHERE date = ?";
        Fact fact = null;
        int dateInt = Integer.parseInt(dateStr);

        try (Connection conn = connectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dateInt);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int date = rs.getInt("date");
                String factText = rs.getString("fact");
                fact = new Fact(date, factText);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return fact;
    }
}