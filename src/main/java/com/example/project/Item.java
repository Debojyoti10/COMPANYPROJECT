package main.java.com.example.project;

import java.util.ArrayList;
import java.util.List;

/**
 * A sellable Item. Knows which Orders include it.
 */
public class Item {
    private final int itemNo;
    private final double rate;
    private final String description;
    private Company company;
    private final List<Order> orders = new ArrayList<>();

    public Item(int itemNo, double rate, String description) {
        this.itemNo = itemNo;
        this.rate = rate;
        this.description = description;
    }

    /** Called by Company.addItem(...) */
    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }


    public int getItemNo() {
        return itemNo;
    }

    public double getRate() {
        return rate;
    }

    public String getDescription() {
        return description;
    }

    /** Called by Order.addOrderItem(...) */
    void addOrder(Order o) {
        if (!orders.contains(o)) {
            orders.add(o);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}