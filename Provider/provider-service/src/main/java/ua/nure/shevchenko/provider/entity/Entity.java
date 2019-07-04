package ua.nure.shevchenko.provider.entity;

public abstract class Entity {
    private static final long serialVersionUID = 8466257860808346236L;

    private long id;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
