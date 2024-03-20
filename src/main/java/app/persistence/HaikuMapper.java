package app.persistence;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */

import app.entities.HaikuPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HaikuMapper {

    public static String generateHaikuPart(ConnectionPool connectionPool, boolean isFiveSyllables, boolean madeByKevin, boolean aboutKevin){
        List<HaikuPart> haikuParts = new ArrayList<>();
        String sql = "SELECT * FROM haiku_parts WHERE is_5_syllables = ? AND made_by_kevin = ? AND about_kevin = ?";
        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        )
        {
            ps.setBoolean(1,isFiveSyllables);
            ps.setBoolean(2,madeByKevin);
            ps.setBoolean(3,aboutKevin);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int partId = rs.getInt("part_id");
                String text = rs.getString("text");
                haikuParts.add(new HaikuPart(partId,text));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Random random = new Random();
        int rndNumber = random.nextInt(0,haikuParts.size());
        return haikuParts.get(rndNumber).getText();
    }
}
