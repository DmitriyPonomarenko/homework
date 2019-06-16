package part3.lesson15.entity;

public enum RoleName {
    ADMINISTRATION(1, "Administration"),
    CLIENTS(2, "Clients"),
    BILLING(3, "Billing");

    public final Integer number;
    public final String name;

    RoleName(Integer number, String name) {
        this.number = number;
        this.name = name;
    }
}
