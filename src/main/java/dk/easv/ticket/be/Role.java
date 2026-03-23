package dk.easv.ticket.be;

public enum Role {
    ADMIN("Admin"),
    COORDINATOR("Coordinator");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
