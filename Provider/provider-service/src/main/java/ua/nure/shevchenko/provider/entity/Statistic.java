package ua.nure.shevchenko.provider.entity;

import java.util.Objects;

public class Statistic {

    private String tariffName;

    private double money;

    private  int amountUsers;


    public Statistic(String tariffName, double money, int amountUsers) {
        this.tariffName = tariffName;
        this.money = money;
        this.amountUsers = amountUsers;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getAmountUsers() {
        return amountUsers;
    }

    public void setAmountUsers(int amountUsers) {
        this.amountUsers = amountUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(tariffName, statistic.tariffName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffName);
    }
}
