package main.java.com.example.project;

import java.time.LocalDate;

/**
 * A Customer that has a discount percentage applied to their total.
 */
public class RegisteredCustomer extends Customer {
    private final double discountPercent;   // e.g. 10.0 for 10%
    private String membershipType;          
    private double membershipFees;          
    private double membershipDiscount;
    private LocalDate dtReg;


    public RegisteredCustomer(int id, String name, double discountPercent) {
        super(id, name);
        this.discountPercent = discountPercent;
    }

    public double getDiscountPercent() {
        return (membershipType != null) 
             ? membershipDiscount 
             : discountPercent;
    }


    public void assignMembership(String type) {
        switch(type.trim().toUpperCase()) {
            case "SILVER":
                membershipType     = "SILVER";
                membershipFees     = 150.0;
                membershipDiscount =  7.5;
                break;
            case "GOLD":
                membershipType     = "GOLD";
                membershipFees     = 300.0;
                membershipDiscount = 12.0;
                break;
            case "PLATINUM":
                membershipType     = "PLATINUM";
                membershipFees     = 500.0;
                membershipDiscount = 20.0;
                break;
            default:
                throw new IllegalArgumentException("Unknown membership: " + type);
        }
    }

    public String getTypeOfMembership()  { return membershipType; }
    public double getFees()             { return membershipFees; }
    public double getMembershipDiscount() { return membershipDiscount; }


    public double getRawTotalValue() {
        return super.getTotalValue();
    }


    /**
     * Override: apply discount to the sum of orders.
     */
    @Override
    public double getTotalValue() {
        double raw = super.getTotalValue();
        double disc = getDiscountPercent();
        return raw * (100.0 - disc) / 100.0;
    }

    public void setDtReg(LocalDate dtReg) {
        this.dtReg = dtReg;
    }

    public void setDtReg(String dtReg) {
        this.dtReg = LocalDate.parse(dtReg);
    }

    public String getDtReg() {
        return (dtReg == null ? null : dtReg.toString());
    }

    
}
