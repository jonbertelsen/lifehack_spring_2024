package app.entities;

import java.time.LocalDate;

public class PizzaRecipe
{
    private int recipeId;
    private int quantity;
    private int weightPerBall;
    private int hydration;
    private int temperature;
    private LocalDate date;

    public PizzaRecipe(int quantity, int weightPerBall, int hydration, int temperature)
    {
        this.quantity = quantity;
        this.weightPerBall = weightPerBall;
        this.hydration = hydration;
        this.temperature = temperature;
        this.date = LocalDate.now();
    }

    public PizzaRecipe(int recipeId, int quantity, int weightPerBall, int hydration, int temperature, LocalDate date)
    {
        this.recipeId = recipeId;
        this.quantity = quantity;
        this.weightPerBall = weightPerBall;
        this.hydration = hydration;
        this.temperature = temperature;
        this.date = date;
    }

    public int getRecipeId()
    {
        return recipeId;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getWeightPerBall()
    {
        return weightPerBall;
    }

    public int getHydration()
    {
        return hydration;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public LocalDate getDate()
    {
        return date;
    }

    /*** Calc *****/

    public int getTotalWeight()
    {
        return quantity * weightPerBall;
    }

    public int getFlourInGrammes()
    {
        return roundToNearest25 ((int) (getTotalWeight() / (1 + hydration / 100.0)));
    }

    public int getPoolishFlourInGrammes()
    {
        return roundToNearest25 ( (int) (getFlourInGrammes() * 0.3));
    }

    public int getPoolishWaterInGrammes()
    {
        return roundToNearest25 ((int) (getFlourInGrammes() * 0.3));
    }

    public int getWaterInGrammes()
    {
        return (int) (getTotalWeight() - getFlourInGrammes());
    }

    public int getYeastInGrammes()
    {
        return (int) (getFlourInGrammes() * 0.02);
    }

    public int getSaltInGrammes()
    {
        return (int) (getFlourInGrammes() * 0.02);
    }

    public int getHoneyInGrammes()
    {
        return (int) (getFlourInGrammes() * 0.01);
    }

    public int getCanOfTomatoesInNumbers()
    {
        return (int) Math.ceil(quantity / 4.0);
    }

    public int getMozzarellaInGrammes()
    {
        return quantity * 100;
    }

    public int getFinalFlourInGrammes()
    {
        return getFlourInGrammes() + getPoolishFlourInGrammes();
    }

    public int getFinalWaterInGrammes()
    {
        return getWaterInGrammes() + getPoolishWaterInGrammes();
    }

    private int roundToNearest25(int number)
    {
        return (int) Math.floor(number / 25.0) * 25;
    }

}
