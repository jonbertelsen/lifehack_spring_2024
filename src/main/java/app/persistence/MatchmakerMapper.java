package app.persistence;

import app.entities.MatchMakerUser;
import app.entities.User;
import app.exceptions.DatabaseException;
import io.javalin.http.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public static void createuser(String userName, String userPassword,String firstName,String lastName,int age, String gender, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "INSERT INTO public.matchmaker_user(username,firstname,lastname, age, gender,userpassword) VALUES ( ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, userName);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setInt(4, age);
            ps.setString(5, gender);
            ps.setString(6, userPassword);


            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl ved oprettelse af ny bruger");
            }
        }
        catch (SQLException e)
        {
            String msg = "Der er sket en fejl. Prøv igen";
            if (e.getMessage().startsWith("ERROR: duplicate key value "))
            {
                msg = "Brugernavnet findes allerede. Vælg et andet";
            }
            throw new DatabaseException(msg, e.getMessage());
        }
    }
    public static void createPreference(String hairColor, String eyeColor, String sex, String race, ConnectionPool connectionPool) throws DatabaseException {

        String sql = "insert into preference (haircolor, eyecolor, sex, race) values (?, ?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, hairColor);
            ps.setString(2, eyeColor);
            ps.setString(3, sex);
            ps.setString(4, race);

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

    public static String getPhotoURL(int fugitivesId, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "select photo_url from fugitives where fugitives_id";

        try(
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ps.setInt(1, fugitivesId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("photo_url");
            } else {
                throw new DatabaseException("Fugitive med id " + fugitivesId +" kunne ikke findes");
            }
        } catch (SQLException e){
            String msg = "Fejl ved indhentning af billede";
            throw new DatabaseException(msg, e.getMessage());
        }

    }
}