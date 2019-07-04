package ua.nure.shevchenko.provider.entity;

import java.util.Date;

public class TariffConnectionInfo extends Entity {

    private long userId;
    private long tariffId;
    private String tariffName;
    private long serviceId;
    private Date period;
    private double money;
    private boolean debt;

    private User user;


    public TariffConnectionInfo() {
    }

    public TariffConnectionInfo(long userId, long tariffId, Date period) {
        this.userId = userId;
        this.tariffId = tariffId;
        this.period = period;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }


    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTariffId() {
        return tariffId;
    }

    public void setTariffId(long tariffId) {
        this.tariffId = tariffId;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public boolean isDebt() {
        return debt;
    }

    public void setDebt(boolean debt) {
        this.debt = debt;
    }

    @Override
    public String toString() {
        return "TariffConnectionInfo{" +
                "userId=" + userId +
                ", tariffId=" + tariffId +
                ", tariffName='" + tariffName + '\'' +
                ", debt=" + debt +
                '}';
    }
}
