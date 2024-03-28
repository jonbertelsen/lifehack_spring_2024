package app.persistence;

import app.entities.MatchMakerUser;
import app.entities.MatchmakerFugitive;
import app.entities.User;
import app.exceptions.DatabaseException;
import io.javalin.http.Context;

import javax.xml.namespace.QName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchmakerMapper
{

    public static MatchMakerUser login(String userName, String password, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "select * from matchmaker_user where username=? and userpassword=?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("user_id");
                return new MatchMakerUser(id, userName, password);
            } else
            {
                throw new DatabaseException("Fejl i login. Prøv igen");
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("DB fejl", e.getMessage());
        }
    }

    public static int createuser(String userName, String userPassword, String firstName, String lastName, int age, String gender, ConnectionPool connectionPool) throws DatabaseException {
        int userId = -1; // Default value if user ID retrieval fails

        String sql = "INSERT INTO public.matchmaker_user(username, firstname, lastname, age, gender, userpassword) VALUES (?, ?, ?, ?, ?, ?) RETURNING user_id";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setInt(4, age);
            ps.setString(5, gender);
            ps.setString(6, userPassword);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("user_id");
            } else {
                throw new DatabaseException("Kunne ikke få genereret bruger-ID efter oprettelse.");
            }
        } catch (SQLException e) {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value ")) {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }

        return userId;
    }

    public static void createPreference(int userid,String hairColor, String eyeColor, String sex, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "insert into preference (user_id, haircolor, eyecolor, sex) values (?, ?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setInt(1,userid);
            ps.setString(2, hairColor);
            ps.setString(3, eyeColor);
            ps.setString(4, sex);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }


        } catch (SQLException e){
            String msg = "Der er sket en fejl. Prøv igen";
            throw new DatabaseException(msg, e.getMessage());
        }

    }


    public static void likeFugitive(int userId, int fugitiveId, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "insert into liked (fk_user_id, fk_fugitives) values (?, ?)";

        try(
           Connection connection = connectionPool.getConnection();
           PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, userId);
            ps.setInt(2, fugitiveId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved indsættelse af like");
            }
        } catch (SQLException e){
            String msg = "Like er gået forkert, prøv igen";
            throw new DatabaseException(msg, e.getMessage());
        }
    }

    public static String getPhotoURL(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT f.photo_url " +
                "FROM fugitives f " +
                "JOIN preference p ON f.haircolor = p.haircolor " +
                "AND f.eyecolor = p.eyecolor " +
                "AND f.sex = p.sex " +
                "WHERE p.user_id = ? " +
                "ORDER BY f.fugitives_id " +
                "LIMIT 1";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("photo_url");
            } else {
                throw new DatabaseException("No matching fugitive found for user " + userId);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving photo URL", e.getMessage());
        }
    }
    public static List<MatchmakerFugitive> getphoturlAndFugitives_id(int userId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT f.fugitives_id, f.photo_url, f.name, f.description, f.category, f.occupation FROM preference AS p " +
                "JOIN matchmaker_user AS mmu ON mmu.user_id = p.user_id " +
                "JOIN fugitives AS f ON f.sex = p.sex " +
                "WHERE p.user_id = ?";

        MatchmakerFugitive fugitive = null;
        List<MatchmakerFugitive> list=new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String photourl = rs.getString("photo_url");
                int fugitivesId = rs.getInt("fugitives_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                String occupation = rs.getString("occupation");

                fugitive = new MatchmakerFugitive(fugitivesId, photourl, name, description, category, occupation);
                list.add(fugitive);
            }

        } catch (SQLException e) {
            throw new DatabaseException("Error fetching random fugitive ID for user", e.getMessage());
        }
        return list;
    }

}