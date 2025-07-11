import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Exceptions.RoomUnavailableException;
import Exceptions.ServiceNotFoundException;
import Hotel.Booking;
import Hotel.Guest;
import Hotel.Room;
import Services.LaundryService;
import Services.RoomService;
import Services.SpaTreatment;
import Staff.Staff;
import Services.HotelService;

public class HotelManagementCore {
    private final String name;
    private final List<Booking> bookings;
    private final List<Room> rooms;
    private final List<Staff> staff;
    private final List<HotelService> services;

    public HotelManagementCore(String name) {
        this.name = name;
        this.bookings = new ArrayList<>();
        this.rooms = new ArrayList<>();
        this.staff = new ArrayList<>();
        this.services = new ArrayList<>();
    }

    public List<Booking> getBookings() {
        return bookings;
    }
    public List<Room> getRooms() {
        return rooms;
    }
    public List<Staff> getStaff() {
        return staff;
    }
    public List<HotelService> getServices() {
        return services;
    }
    public String getName() {
        return name;
    }
    public void addRoom(Room room) {
        if (getRooms().contains(room)) {
            System.out.println("Hotel.Room already exists!");
            return;
        }

        getRooms().add(room);
        System.out.println("Hotel.Room added!");
    }
    public void addStaff(Staff newStaff) {
        if (getStaff().contains(newStaff)) {
            System.out.println("Staff member already registered!");
            return;
        }
        getStaff().add(newStaff);
        System.out.println("Staff added!");
    }
    public void addService(HotelService service) throws ServiceNotFoundException{
        if (getServices().contains(service)) {
            System.out.println("Service instance already present!");
            return;
        }
        if (!(service instanceof LaundryService || service instanceof RoomService || service instanceof SpaTreatment)) {
            throw new ServiceNotFoundException();
        }
        getServices().add(service);
        System.out.println("Service added!");
    }

    public void addBooking(Booking booking) {
        //Does the exact same booking object exist?
        if (booking.getStart() == null) return;
        if (getBookings().contains(booking)) return;

        try {
            if (booking.getRoom().isAvailable(bookings, booking.getStart(), booking.getEnd())) {
                System.out.println("Hotel.Booking added!");
                getBookings().add(booking);
            }
        }
        catch (RoomUnavailableException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addBooking(Room room, Guest guest, LocalDate start, LocalDate end) {
            Booking booking = new Booking(room, guest, start, end);
            addBooking(booking); //Calls above method
    }

    public void removeBooking(Booking booking) {
        //Does booking exist?
        if (!getBookings().contains(booking)) {
            System.out.println("Hotel.Booking doesn't exist!");
            return;
        }
        //Else, remove and return true.
        bookings.remove(booking);
    }
    public void removeBooking (UUID ID) {
        for (Booking current : getBookings()) {
            if (current.getID() == ID) {
                removeBooking(current);
                return;
            }
            System.out.println("Couldn't find booking!");
        }
    }

    public void removeStaff(Staff myStaff) {
        if (!staff.contains(myStaff)) {
            System.out.println("Staff member doesn't exist!");
            return;
        }
        staff.remove(myStaff);
        System.out.println("Staff removed successfully!");
    }
    public void removeStaff (UUID ID) {
        for (Staff current : getStaff()) {
            if (current.getID() == ID) {
                removeStaff(current);
                return;
            }
            System.out.println("Couldn't find staff member!");
        }
    }

    public void displayBookings() {
        //If bookings list is empty
        if(getBookings().isEmpty()) {
            System.out.println("No bookings exist in " + getName() + "!");
            return; //End method
        }
        //Else, print all bookings
            System.out.println("Existing bookings in " + getName() + ": ");
            for (Booking current : getBookings())
                System.out.println(current);
    }
    public void displayRooms(){
        //If rooms list is empty
        if(getRooms().isEmpty()) {
            System.out.println("No rooms exist in " + getName() + "!");
            return; //End method
        }
        //Else, print all rooms
        System.out.println("Existing rooms in " + getName() + ": ");
        for (Room current : getRooms())
            System.out.println(current);
    }
    public void displayStaff(){
        //If rooms list is empty
        if(getStaff().isEmpty()) {
            System.out.println("No staff exist in " + getName() + "!");
            return; //End method
        }
        //Else, print all rooms
        System.out.println("Existing staff in " + getName() + ": ");
        for (Staff current : getStaff())
            System.out.println(current);
    }
    public void displayServices(){
        //If rooms list is empty
        if(getServices().isEmpty()) {
            System.out.println("No services exist in " + getName() + "!");
            return; //End method
        }
        //Else, print all rooms
        System.out.println("Existing services in " + getName() + ": ");
        for (HotelService current : getServices())
            System.out.println(current);
    }

    public BigDecimal bookingTotal (Booking booking, List<HotelService> usedServices) {
        BigDecimal total = new BigDecimal(0);
        for (HotelService current : usedServices) {
            total = total.add(current.getTotalCost());
        }
        return total.add(booking.getCost());
    }

    public void workStaff() {
        for (Staff staffer : staff) {
            staffer.performDuty();
        }
    }

}
