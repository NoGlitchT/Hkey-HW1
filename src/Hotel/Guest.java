package Hotel;

import java.util.UUID;

public class Guest {
    private final String fullName;
    private final String email;
    private final UUID ID;

    public Guest(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
        ID = UUID.randomUUID();
    }

    public UUID getID() {
        return ID;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return "Name & Surname: " + getFullName() +
                "\nEmail Address: " + getEmail() +
                "\nID: " + getID();
    }
}
