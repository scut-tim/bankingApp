package bean;

public class Securities {
    private String name;
    private double price;
    private double change;

    public Securities(String x, double y, double z) {
        setName(x);
        setPrice(y);
        setChange(z);
    }

    public void setName(String x) {
        name = x;
    }

    public void setPrice(double x) {
        price = x;
    }

    public void setChange(double x) {
        change = x;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getChange() {
        return change;
    }


}
