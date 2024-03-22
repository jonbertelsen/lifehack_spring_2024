package app.persistence;

import app.entities.Plante;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Rouvillain
 */

public class PlanteMapper {






    public static List<Plante> getAllPlantsPerUser (int userID, ConnectionPool connectionPool) {

        List<Plante> planteList = new ArrayList<>();
        String sql = "select u.user_id,p.plante_id, p.navn,p.placering,v.dato from plante p "+
                " join vandtid v on p.plante_id=v.plante_id " +
                " join users u on p.user_id=u.user_id" +
                " where u.user_id=? "+
                " order by u.user_id";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)

        )
        {

            ps.setInt(1,userID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                int id = rs.getInt("user_id");
                int planteid = rs.getInt("plante_id");
                String name = rs.getString("navn");
                String placering = rs.getString("placering");
                Date sqlDate = rs.getDate("dato");
                LocalDate localDate = sqlDate.toLocalDate();
                planteList.add(new Plante(planteid,name, placering,id, localDate));

            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return planteList;


    }
    public static void deletePlanteAndVandtid(int planteId, ConnectionPool connectionPool) throws DatabaseException {
        String deleteVandtidSql = "DELETE FROM vandtid WHERE plante_id = ?";
        String deletePlanteSql = "DELETE FROM plante WHERE plante_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement deleteVandtidStatement = connection.prepareStatement(deleteVandtidSql);
                PreparedStatement deletePlanteStatement = connection.prepareStatement(deletePlanteSql)
        ) {

            deleteVandtidStatement.setInt(1, planteId);
            deleteVandtidStatement.executeUpdate();


            deletePlanteStatement.setInt(1, planteId);
            int rowsAffected = deletePlanteStatement.executeUpdate();

            if (rowsAffected != 1) {
                throw new DatabaseException("Failed to delete plant");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error deleting plant: " + e.getMessage());
        }
    }



    public static void updateDato(int planteId, ConnectionPool connectionPool) throws DatabaseException
    {
        String sql = "update vandtid set dato = ? where plante_id = ?";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            ps.setDate(1, sqlDate);
            ps.setInt(2, planteId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected != 1) {
                throw new DatabaseException("Fejl i opdatering af en plante");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i opdatering af en plante", e.getMessage());
        }
    }




    public static Plante addPlante(User user, String navn, String placering, ConnectionPool connectionPool) throws DatabaseException {
        Plante newPlante = null;

        String planteSql = "INSERT INTO plante (navn, placering, user_id) VALUES (?, ?, ?)";
        String vandtidSql = "INSERT INTO vandtid (dato, plante_id) VALUES (?, ?)";

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement psPlante = connection.prepareStatement(planteSql, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psVandtid = connection.prepareStatement(vandtidSql)) {

            psPlante.setString(1, navn);
            psPlante.setString(2, placering);
            psPlante.setInt(3, user.getUserId());
            int rowsAffectedPlante = psPlante.executeUpdate();

            if (rowsAffectedPlante == 1) {
                ResultSet rsPlante = psPlante.getGeneratedKeys();
                rsPlante.next();
                int newPlanteId = rsPlante.getInt(1);
                newPlante = new Plante(newPlanteId, navn, placering, user.getUserId());

                java.util.Date currentDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
                psVandtid.setDate(1, sqlDate);
                psVandtid.setInt(2, newPlanteId);
                int rowsAffectedVandtid = psVandtid.executeUpdate();
                if (rowsAffectedVandtid == 1) {
                    ResultSet rsVandtid = psVandtid.getGeneratedKeys();
                    rsVandtid.next();
                    int newVandtidId = rsVandtid.getInt(1);

                } else {
                    throw new DatabaseException("Fejl under indsætning af vandtid for planten: " + navn);
                }
            } else {
                throw new DatabaseException("Fejl under indsætning af planten: " + navn);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl i DB connection", e.getMessage());
        }
        return newPlante;
    }


            }
