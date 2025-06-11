package main.java.com.example.project;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Company that holds Customers and Items.
 * getTotalOrderValuev2() simply sums each Customer.getTotalValue().
 */
public class Company {
    private final String name;
    private final List<Customer> customers = new ArrayList<>();
    private final List<Item> items = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Add a Customer and link back.
     */
    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
            customer.setCompany(this);
        }
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Add an Item (no change here).
     */
    public void addItem(Item item) {
        if (!items.contains(item)) {
            items.add(item);
            item.setCompany(this);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     * Version 2: delegate sum + discount logic to Customer.getTotalValue().
     */
    public double getTotalOrderValuev1() {
        double total = 0.0;
        for (Customer c : customers) {
            for (Order o : c.getOrders()) {
                for (Item it : o.getItems()) {
                    double line = it.getRate();
                    if (c instanceof RegisteredCustomer) {
                        double disc = ((RegisteredCustomer) c).getDiscountPercent();
                        line = line * (100.0 - disc) / 100.0;
                    }
                    total += line;
                }
            }
        }
        return total;
    }

    /**
     * Version 2: simply sum Customer.getTotalValue(), 
     * which encapsulates raw vs. discounted logic.
     */
    public double getTotalOrderValuev2() {
        double total = 0.0;
        for (Customer c : customers) {
            total += c.getTotalValue();
        }
        return total;
    }
}