package main.java.com.example.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private LocalDate orderDate;
    private Customer customer;
    private List<Item> items = new ArrayList<>();

    public Order(int id, LocalDate orderDate) {
        this.id = id;
        this.orderDate = orderDate;
    }

    /** Link this order to the placing customer */
    void setCustomer(Customer c) {
        this.customer = c;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    /** 
     * Add an item to this order. 
     * Also updates the Item’s side of the relationship.
     */
    public void addItem(Item item) {
        items.add(item);
        item.addOrder(this);
    }

    public List<Item> getItems() {
        return items;
    }

    /** 
     * Compute total, applying discount if the customer is registered 
     */
    public double calculateTotal() {
        double sum = items.stream().mapToDouble(Item::getRate).sum();
        if (customer instanceof RegisteredCustomer) {
            double disc = ((RegisteredCustomer)customer).getDiscountPercent();
            sum = sum * (100.0 - disc) / 100.0;
        }
        return sum;
    }
}

