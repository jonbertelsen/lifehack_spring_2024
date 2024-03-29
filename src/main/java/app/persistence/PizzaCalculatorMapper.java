package app.persistence;

import app.entities.PizzaRecipe;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PizzaCalculatorMapper
{

    public static List<PizzaRecipe> getRecipes(int userId, ConnectionPool connectionPool) throws DatabaseException
    {
        List<PizzaRecipe> recipeList = new ArrayList<>();
        String sql = "SELECT * FROM pizza_recipe WHERE user_id = ?";
        try (Connection connection = connectionPool.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                int recipeId = resultSet.getInt("recipe_id");
                int quantity = resultSet.getInt("quantity");
                int weight = resultSet.getInt("weight");
                int hydration = resultSet.getInt("hydration");
                int temperature = resultSet.getInt("temperature");
                LocalDate date = resultSet.getDate("created").toLocalDate();
                PizzaRecipe recipe = new PizzaRecipe(recipeId, quantity, weight, hydration, temperature, date);
                recipeList.add(recipe);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Error getting recipes", e.getMessage());
        }
        return recipeList;
    }

    public static PizzaRecipe getRecipeById(int recipeId, ConnectionPool connectionPool) throws DatabaseException
    {
        PizzaRecipe recipe = null;
        String sql = "SELECT * FROM pizza_recipe WHERE recipe_id = ?";
        try (Connection connection = connectionPool.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                int quantity = resultSet.getInt("quantity");
                int weight = resultSet.getInt("weight");
                int hydration = resultSet.getInt("hydration");
                int temperature = resultSet.getInt("temperature");
                LocalDate date = resultSet.getDate("created").toLocalDate();
                recipe = new PizzaRecipe(recipeId, quantity, weight, hydration, temperature, date);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Error getting recipes", e.getMessage());
        }
        return recipe;
    }

    public static void createRecipe(PizzaRecipe recipe, int userId, ConnectionPool connectionPool) throws DatabaseException
    {

        // Insert the recipe into the database
        String sql = "INSERT INTO pizza_recipe (quantity, weight, hydration, temperature, created, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = connectionPool.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipe.getQuantity());
            statement.setInt(2, recipe.getWeightPerBall());
            statement.setInt(3, recipe.getHydration());
            statement.setInt(4, recipe.getTemperature());
            statement.setDate(5, java.sql.Date.valueOf(recipe.getDate()));
            statement.setInt(6, userId);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Error creating recipe", e.getMessage());
        }
    }

    public static void deleteRecipe(int recipeId, ConnectionPool connectionPool) throws DatabaseException
    {
        // Delete the recipe from the database
        String sql = "DELETE FROM pizza_recipe WHERE recipe_id = ?";
        try (Connection connection = connectionPool.getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, recipeId);
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Error deleting recipe", e.getMessage());
        }
    }


}
