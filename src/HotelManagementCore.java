import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class HotelManagementCore {
    private final String name;
    private final ArrayList<Booking> bookings;
    private final ArrayList<Room> rooms;

    public HotelManagementCore(String name) {
        this.name = name;
        this.bookings = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }
    public ArrayList<Room> getRooms() {
        return rooms;
    }
    public String getName() {
        return name;
    }

    public void addRoom(Room room) {
        if (getRooms().contains(room)) {
            System.out.println("Room already exists!");
            return;
        }

        getRooms().add(room);
        System.out.println("Room added!");
    }

    public boolean isRoomAvailable(Booking booking) {
        //For every room in arraylist
        for (Booking b : getBookings()) {
            if (b.getRoom().equals(booking.getRoom())) {
                //Do dates overlap?
                if (booking.getStart().isBefore(b.getEnd()) && booking.getEnd().isAfter(b.getStart())) {
                    return false;
                }
                //Are dates the same?
                if (booking.getStart().equals(b.getStart()) && booking.getEnd().equals(b.getEnd())) {
                    return false;
                }
            }
        }
        //Else, return true
        return true;
    }

    public boolean addBooking(Booking booking) {
        //If invalid date
        if (!booking.getStart().isBefore(booking.getEnd())) {
            System.out.println("Invalid date!");
            return false;
        }
        //Does the exact same booking object exist?
        if (getBookings().contains(booking)) return false;
        // Check if any bookings who share a room have the OVERLAPPING dates
        if (!isRoomAvailable(booking)){
            System.out.println("Room unavailable!");
            return false;
        }
        //Else, add booking
        System.out.println("Booking added!");
        getBookings().add(booking);
        return true;
    }
    public boolean addBooking(Room room, Guest guest, LocalDate start, LocalDate end) {
        Booking booking = new Booking(room, guest, start, end);
        return addBooking(booking); //Calls above method
    }

    public boolean removeBooking(Booking booking) {
        //Does booking exist?
        if (!getBookings().contains(booking)) {
            System.out.println("Booking doesn't exist!");
            return false;
        }
        //Else, remove and return true.
        bookings.remove(booking);
        return true;
    }
    public boolean removeBooking (UUID ID) {
        for (Booking current : getBookings()) {
            if (current.getID() == ID) {
                return removeBooking(current);
            }
            System.out.println("Couldn't find booking!");
        }
        return false;
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
        //Else, print all bookings
        System.out.println("Existing rooms in " + getName() + ": ");
        for (Room current : getRooms())
            System.out.println(current);
    }



}
