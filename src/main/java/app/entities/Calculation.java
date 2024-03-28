package app.entities;

public class Calculation {

    private double savingPercentage;
    private int income;
    private int rent;
    private int goal;


    public Calculation(double savingPercentage, int income, int rent, int goal) {

        this.savingPercentage = savingPercentage;
        this.income = income;
        this.rent = rent;
        this.goal = goal;
    }

    public int getResult() {
        // Perform your calculation here
        // Example calculation: (income - rent) * (savingPercentage / 100)
        double result=(income - rent) * (savingPercentage / 100);
        return (int) result;
    }

    public double getSavingPercentage() {
        return savingPercentage;
    }

    public int getIncome() {
        return income;
    }

    public int getRent() {
        return rent;
    }

    public int getGoal() {
        return goal;
    }

    @Override
    public String toString() {
        return "Calculation{" +

                "savingPercentage=" + savingPercentage +
                ", income=" + income +
                ", rent=" + rent +
                ", goal=" + goal +
                '}';
    }
}