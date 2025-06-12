package main.java.com.example.project;

public class MembershipFactory {
    private static final MembershipFactory INSTANCE = new MembershipFactory();
    private MembershipFactory() {}

    public static MembershipFactory getInstance() {
        return INSTANCE;
    }

    public Membership createMembership(String type, double fees, double discount) {
        // optionally cache by type if you want singletons per tier
        return new Membership(type, fees, discount);
    }
}