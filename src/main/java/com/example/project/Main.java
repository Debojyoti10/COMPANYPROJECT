package main.java.com.example.project;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // 1) Create the company
        Company acme = new Company("ACME Supplies");

        // 2) Add some items
        Item widget = new Item(1001,  9.99, "Standard Widget");
        Item gadget = new Item(1002, 14.49, "Deluxe Gadget");
        acme.addItem(widget);
        acme.addItem(gadget);

        // 3) Register two customers
        Customer alice = new Customer(1, "Alice");
        RegisteredCustomer bob = new RegisteredCustomer(2, "Bob", 10.0);  // old‐style 10% fallback

        // ✦ NEW: assign Bob a SILVER membership tier in addition to his constructor discount
        bob.assignMembership("SILVER");
        // ✦ OPTIONAL: record when Bob signed up
        bob.setDtReg(LocalDate.of(2024, 1, 1));

        acme.addCustomer(alice);
        acme.addCustomer(bob);

        // 4) Alice places an order for 2 widgets
        Order order1 = new Order(501, LocalDate.now());
        alice.placeOrder(order1);
        order1.addItem(widget);
        order1.addItem(widget);

        // 5) Bob places an order for 1 gadget and 1 widget
        Order order2 = new Order(502, LocalDate.now());
        bob.placeOrder(order2);
        order2.addItem(gadget);
        order2.addItem(widget);

        // 6) Print out all orders for each customer
        for (Customer c : acme.getCustomers()) {
            System.out.println("Customer: " + c.getName());
            for (Order o : c.getOrders()) {
                System.out.println("  Order #" + o.getId() + " on " + o.getOrderDate());
                o.getItems().forEach(item ->
                    System.out.println("    - " + item.getDescription() + " @ " + item.getRate())
                );
                System.out.printf("    Total: $%.2f%n", o.calculateTotal());
            }
            System.out.println();
        }

        // 7) NEW: Show membership details and raw vs. discounted totals
        for (Customer c : acme.getCustomers()) {
            if (c instanceof RegisteredCustomer) {
                RegisteredCustomer rc = (RegisteredCustomer) c;
                System.out.printf("%s's membership: %s (joined %s)%n",
                    rc.getName(), rc.getTypeOfMembership(), rc.getDtReg());
                System.out.printf("  Fees: $%.2f, Discount: %.1f%%%n",
                    rc.getFees(), rc.getMembershipDiscount());
                System.out.printf("  Raw total:   $%.2f%n", rc.getRawTotalValue());
                System.out.printf("  Final total: $%.2f%n%n", rc.getTotalValue());
            } else {
                System.out.printf("%s total:      $%.2f%n%n",
                    c.getName(), c.getTotalValue());
            }
        }

        // 8) NEW: Company‐wide totals and savings
        double v1 = acme.getTotalOrderValuev1();
        double v2 = acme.getTotalOrderValuev2();
        System.out.printf("Company grand total (v1 manual):    $%.2f%n", v1);
        System.out.printf("Company grand total (v2 delegated): $%.2f%n", v2);
        System.out.printf("Total savings from discounts:       $%.2f%n", v1 - v2);
    }
}
