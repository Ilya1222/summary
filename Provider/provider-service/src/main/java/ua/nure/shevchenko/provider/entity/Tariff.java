package ua.nure.shevchenko.provider.entity;

public class Tariff extends Entity {
    private String name;
    private String specification;
    private double price;
    private  int serviceId;

    public Tariff() {
    }

    public Tariff(String name, String specification, double price, int serviceId) {
        this.name = name;
        this.specification = specification;
        this.price = price;
        this.serviceId = serviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", specification='" + specification + '\'' +
                ", price=" + price +
                ", serviceId=" + serviceId +
                '}';
    }
}
