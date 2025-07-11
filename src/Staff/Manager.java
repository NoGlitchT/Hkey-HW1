package Staff;

public class Manager extends Staff{
    public Manager(String fullName, String department) {
        super(fullName, department);
    }
    @Override
    public void performDuty() {
        System.out.println(getFullName() + " is managing the staff...\nID: " + getID());
    }

}
