package app.persistence;

import app.entities.HaikuPart;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Purpose:
 *
 * @Author: Anton Friis Stengaard
 */
public class HaikuMapper {

    public static String generateHaikuPart(ConnectionPool connectionPool, boolean isFiveSyllables, boolean madeByKevin, boolean aboutKevin){
        List<HaikuPart> haikuParts = new ArrayList<>();
        String sql = "select text from haiku_parts where is_5_syllables=? and madeByKevin=? and aboutKevin=?";
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
