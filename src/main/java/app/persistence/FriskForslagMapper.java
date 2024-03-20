package app.persistence;

import app.entities.FriskForslagRecipe;
import app.exceptions.DatabaseException;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriskForslagMapper {
    public static List<FriskForslagRecipe> FilterRecipesIngredients(ConnectionPool cp, String... foodItems) throws DatabaseException {
        List<FriskForslagRecipe> filteredRecipes = new ArrayList<>();

        StringBuilder sqlCondition = new StringBuilder();
        for (int i = 0; i < foodItems.length; ++i){
            sqlCondition.append("'" + foodItems[i] + "' ILIKE ANY(ingredients) ");
            if (i < foodItems.length - 1)
                sqlCondition.append(" AND ");
        }

        String sql = """
                SELECT * FROM friskforslag_opskrift\s
                WHERE\s
                """
                + sqlCondition;

        try (
                Connection c = cp.getConnection();
                PreparedStatement ps = c.prepareStatement(sql);
                ) {
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
                        rs.getString("source")
                ));
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("fejl i søgning af opskrifter");
        }
        System.out.println(filteredRecipes);
        return filteredRecipes;
    }
}
