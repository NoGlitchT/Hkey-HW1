package Staff;


public class FrontDeskStaff extends Staff{

    public FrontDeskStaff(String fullName) {
        super(fullName, "Front Desk");
    }

    @Override
    public void performDuty() {
        greetGuest();
    }
    public void greetGuest() {
        System.out.println("Hello and welcome! My name is " + getFullName() + ".");
    }
    public void greetGuest(String guestName) {
        System.out.println("Hello Mr/Ms " + guestName + "! Welcome!");
    }
}
