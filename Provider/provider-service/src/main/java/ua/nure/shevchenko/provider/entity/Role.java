package ua.nure.shevchenko.provider.entity;

public class Role extends Entity {
    private String name;

    public Role(){

    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
