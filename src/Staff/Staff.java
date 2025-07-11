package Staff;

import Services.HotelService;

import java.util.UUID;

public abstract class Staff {
    protected final UUID ID;
    protected String fullName;
    protected String department;

    protected Staff(String fullName, String department) {
        ID = UUID.randomUUID();
        this.fullName = fullName;
        this.department = department;
    }

    public UUID getID() {
        return ID;
    }
    public String getFullName() {
        return fullName;
    }

    public String getDepartment() {
        return department;
    }

    public abstract void performDuty();

    @Override
    public String toString() {
        return "Employee ID: " + getID() +
                "\nEmployee name: " + getFullName() +
                "\nDepartment: " + getDepartment();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        //Cast to HotelService, check if ID is same
        Staff staff = (Staff) obj;
        return getID() == staff.getID();
    }
}
