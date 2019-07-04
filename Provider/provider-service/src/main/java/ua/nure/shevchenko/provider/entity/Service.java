package ua.nure.shevchenko.provider.entity;

public class Service extends Entity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Service{" +
                "name='" + name + '\'' +
                  "id="+ getId() +   '}';
    }
}
