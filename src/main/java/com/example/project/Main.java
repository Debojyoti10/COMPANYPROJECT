package main.java.com.example.project;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Company acme = new Company("ACME Supplies");

        // -- Items
        Item widget = new Item(1001, 9.99, "Standard Widget");
        Item gadget = new Item(1002, 14.49, "Deluxe Gadget");
        acme.addItem(widget);
        acme.addItem(gadget);

        // -- Customers
        Customer alice = new Customer(1, "Alice");
        RegisteredCustomer bob = new RegisteredCustomer(2, "Bob", 10.0); // 10% discount
        acme.addCustomer(alice);
        acme.addCustomer(bob);

        // -- Orders
        Order o1 = new Order(501, LocalDate.now());
        alice.placeOrder(o1);
        o1.addItem(widget);
        o1.addItem(widget);

        Order o2 = new Order(502, LocalDate.now());
        bob.placeOrder(o2);
        o2.addItem(gadget);
        o2.addItem(widget);

        // -- Print each order (as before)
        for (Customer c : acme.getCustomers()) {
            System.out.println("Customer: " + c.getName());
            for (Order o : c.getOrders()) {
                System.out.println("  Order #" + o.getId() + " on " + o.getOrderDate());
                for (Item it : o.getItems()) {
                    System.out.printf("    - %s @ %.2f%n", it.getDescription(), it.getRate());
                }
                System.out.printf("    Total: $%.2f%n%n", o.calculateTotal());
            }
        }

        // -- New: per-customer raw vs final totals
        for (Customer c : acme.getCustomers()) {
            if (c instanceof RegisteredCustomer) {
                RegisteredCustomer rc = (RegisteredCustomer) c;
                System.out.printf("%s raw total:    $%.2f%n", c.getName(), rc.getRawTotalValue());
                System.out.printf("%s final total:  $%.2f%n%n", c.getName(), rc.getTotalValue());
            } else {
                System.out.printf("%s total:        $%.2f%n%n", c.getName(), c.getTotalValue());
            }
        }

        // -- New: company-wide totals and savings
        double v1 = acme.getTotalOrderValuev1();
        double v2 = acme.getTotalOrderValuev2();
        System.out.printf("Company grand total (v1 manual):     $%.2f%n", v1);
        System.out.printf("Company grand total (v2 delegated):  $%.2f%n", v2);
        System.out.printf("Total savings from discounts:        $%.2f%n", (v1 - v2));
    }
}
