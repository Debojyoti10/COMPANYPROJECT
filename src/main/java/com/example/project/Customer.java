package main.java.com.example.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Customer who can place multiple Orders.
 * getTotalValue() sums each order’s calculateTotal(), 
 * so RegisteredCustomer discounts in Order.calculateTotal() apply automatically.
 */
public class Customer {
    private final int id;
    private final String name;
    private Company company;
    private final List<Order> orders = new ArrayList<>();

    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * Place an order: links this customer and the order.
     */
    public void placeOrder(Order order) {
        if (!orders.contains(order)) {
            orders.add(order);
            order.setCustomer(this);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Sum this customer’s orders.
     * Uses Order.calculateTotal(), which applies any RegisteredCustomer discount.
     *
     * @return total value of this customer’s orders
     */
    public double getTotalValue() {
        double total = 0.0;
        for (Order o : orders) {
            total += o.calculateTotal();
        }
        return total;
    }
}