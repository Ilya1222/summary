package ua.nure.shevchenko.provider.entity;

public class Score extends Entity {
    private double balance;

    public Score(){

    }

    public Score(double balance) {
        this.balance = balance;

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Score{ id =" + getId() + "}";
    }
}
