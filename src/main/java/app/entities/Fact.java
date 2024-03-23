package app.entities;


public class Fact {
    private int date;
    private String fact;

    public Fact(int date, String fact) {
        this.date = date;
        this.fact = fact;
    }

    public int getDate() {
        return date;
    }

    public String getFact() {
        return fact;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "date=" + date +
                ", advice='" + fact + '\'' +
                '}';
    }
}
