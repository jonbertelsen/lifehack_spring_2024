package app.persistence;

import app.entities.FriskForslagRecipe;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FriskForslagMapper
{
    public static int GetNumOfRecipesInDatabase(ConnectionPool cp) throws DatabaseException
    {
        String sql = "SELECT COUNT(*) FROM friskforslag_opskrift";

        try (
                Connection c = cp.getConnection();
                PreparedStatement ps = c.prepareStatement(sql)
                ) {
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getInt("count");
        } catch (SQLException e) {
            throw new DatabaseException("fejl ved tælning af databasen");
        }
        return -1;
    }

        public static List<FriskForslagRecipe> FilterRecipesIngredients (ConnectionPool cp, String...foodItems)
            throws DatabaseException
        {
            List<FriskForslagRecipe> filteredRecipes = new ArrayList<>();

            StringBuilder sqlCondition = new StringBuilder();
            for (int i = 0; i < foodItems.length; ++i) {
                // TODO: guard against injections
                if (!sqlArgEnsureSafe(foodItems[i]))
                    continue;
                sqlCondition.append("ingredients::text ILIKE '%").append(foodItems[i]).append("%'");
                if (i < foodItems.length - 1)
                    sqlCondition.append(" OR ");
            }

            String sql = """
                    SELECT * FROM friskforslag_opskrift\s
                    WHERE\s
                    """
                    + sqlCondition;

            try (
                    Connection c = cp.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    filteredRecipes.add(new FriskForslagRecipe(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("procedure"),
                            (String[]) rs.getArray("ingredients").getArray(),
                            (Long[]) rs.getArray("quantities").getArray(),
                            (String[]) rs.getArray("units").getArray(),
                            rs.getString("source"),
                            rs.getString("author"),
                            rs.getString("img")));
                }
            } catch (SQLException e) {
                throw new DatabaseException("fejl i søgning af opskrifter");
            }
            return filteredRecipes;
        }

        public static FriskForslagRecipe SelectRecipeByName (ConnectionPool cp, String name) throws DatabaseException
        {
            // TODO: guard against injections
            if (!sqlArgEnsureSafe(name))
                return null;
            String sql = "SELECT * FROM friskforslag_opskrift WHERE name ILIKE '" + name + "'";
            try (
                    Connection c = cp.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    return new FriskForslagRecipe(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("procedure"),
                            (String[]) rs.getArray("ingredients").getArray(),
                            (Long[]) rs.getArray("quantities").getArray(),
                            (String[]) rs.getArray("units").getArray(),
                            rs.getString("source"),
                            rs.getString("author"),
                            rs.getString("img"));
                else
                    return null;
            } catch (SQLException e) {
                throw new DatabaseException("fejl i søgning af opskrifter");
            }
        }

        public static void InsertRecipe(ConnectionPool cp, FriskForslagRecipe rec) throws DatabaseException
        {
            String sql = """
                    INSERT INTO friskforslag_opskrift(
                    id, name, description, procedure, ingredients, quantities, units, source, author, img)
                    VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """;
            try (
                    Connection c = cp.getConnection();
                    PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, rec.Name());
                ps.setString(2, rec.Descr());
                ps.setString(3, rec.Proc());
                ps.setArray(4, c.createArrayOf("varchar", rec.Ingredients()));
                ps.setArray(5, c.createArrayOf("bigint", rec.Quantities()));
                ps.setArray(6, c.createArrayOf("varchar", rec.Units()));
                ps.setString(7, rec.Src());
                ps.setString(8, rec.Author());
                ps.setString(9, rec.Img());

                ps.executeUpdate();

            } catch (SQLException e) {
                throw new DatabaseException("Fejl ved indsættelse af opskriften '" +
                        rec.Name() +
                        "' i database, findes opskriften allerede?");
            }
        }

        private static boolean sqlArgEnsureSafe (String arg)
        {
            Pattern p = Pattern.compile("[;:,\t\r\n]");
            Matcher m = p.matcher(arg);
            return !m.matches();
        }
    }
