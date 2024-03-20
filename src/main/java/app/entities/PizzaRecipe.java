package app.entities;

import java.time.LocalDate;

public class PizzaRecipe
{
    private int quantity;
    private int weight;
    private int hydration;
    private int temperature;
    private LocalDate date;

    public PizzaRecipe(int quantity, int weight, int hydration, int temperature)
    {
        this.quantity = quantity;
        this.weight = weight;
        this.hydration = hydration;
        this.temperature = temperature;
        this.date = LocalDate.now();
    }

    public int getQuantity()
    {
        return quantity;
    }

    public int getWeight()
    {
        return weight;
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

    public int getFlourInGrammes()
    {
        return (int) (700 / 4 * quantity);
    }

    public int getWaterInGrammes()
    {
        return (int) (500.0 / 4 * quantity);
    }

    public int getYeastInGrammes()
    {
        return (int) (10.0 / 4 * quantity) ;
    }

    public int getSaltInGrammes()
    {
        return (20 / 4) * quantity;
    }

    public int getHoneyInGrammes()
    {
        return (int) (5 / 4 * quantity);
    }

    public int getCanOfTomatoesInNumbers()
    {
        return (int) Math.ceil(quantity / 4);
    }

    public int getMozzarellaInGrammes()
    {
        return quantity * 100;
    }


}
