package Staff;

public class HouseKeepingStaff extends Staff{
    public HouseKeepingStaff(String fullName) {
        super(fullName, "Housekeeping");
    }

    @Override
    public void performDuty() {
        System.out.println("Employee: " + fullName + " has cleaned the room." + "\nID: " + getID());
    }

}
