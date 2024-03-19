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
        String sql = "select * from users where userName=? and UserPassword=?";

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
        String sql = "INSERT INTO public.matchmaker_user(UserName,UserPassword, FirstName,LastName, age, gender)+ VALUES ( ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        )
        {
            ps.setString(1, userName);
            ps.setString(2, userPassword);
            ps.setString(3,firstName);
            ps.setString(4,lastName);
            ps.setInt(5,age);
            ps.setString(6,gender);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1)
            {
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
}