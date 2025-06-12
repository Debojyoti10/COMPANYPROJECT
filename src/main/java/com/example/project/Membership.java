package main.java.com.example.project;

public class Membership {
    private final String type;
    private final double fees;
    private final double discount;    // percent, e.g. 7.5

    public Membership(String type, double fees, double discount) {
        this.type = type;
        this.fees = fees;
        this.discount = discount;
    }

    public String getType()       { return type; }
    public double getFees()       { return fees; }
    public double getDiscount()   { return discount; }
}
