package main.java.com.example.project;

/**
 * A Customer that has a discount percentage applied to their total.
 */
public class RegisteredCustomer extends Customer {
    private final double discountPercent;   // e.g. 10.0 for 10%

    public RegisteredCustomer(int id, String name, double discountPercent) {
        super(id, name);
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public double getRawTotalValue() {
        return super.getTotalValue();
    }


    /**
     * Override: apply discount to the sum of orders.
     */
    @Override
    public double getTotalValue() {
        double subtotal = super.getTotalValue();
        return subtotal * (100.0 - discountPercent) / 100.0;
    }
}
