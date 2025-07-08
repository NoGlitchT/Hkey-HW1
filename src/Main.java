import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        HotelManagementCore hKey = new HotelManagementCore("HKey Hotel");
        //Showing that both bookings and Rooms start empty
        hKey.displayBookings();
        hKey.displayRooms();

        //Default room constructor
        Room room1 = new Room();
        System.out.println(room1); //Showing how toString() looks for room class
        System.out.println("---------------------------------------------");
        //Other room constructor
        Room room2 = new Room(Room.RoomType.Suite);
        Room room3 = new Room(Room.RoomType.Deluxe);

        //Guests
        Guest guest1 = new Guest("Alban Albani", "alban@albanmail.com");
        Guest guest2 = new Guest("Filan Njeri", "random@fakemail.com");
        Guest guest3 = new Guest("John Kompjuteri", "computerman@crazymail.com");
        Guest guest4 = new Guest("Bob Ross", "painterman@artmail.com");
        System.out.println(guest1); //Showing how toString() looks for guest class
        System.out.println("---------------------------------------------");

        //Sample dates
        LocalDate date1 = LocalDate.of(2023, 5, 14);
        LocalDate date2 = LocalDate.of(2024, 1, 22);
        LocalDate date3 = LocalDate.of(2022, 11, 9);
        LocalDate date4 = LocalDate.of(2023, 7, 30);
        LocalDate date5 = LocalDate.of(2024, 9, 5);
        LocalDate date6 = LocalDate.of(2026, 12, 1);
        LocalDate date7 = LocalDate.of(2022, 6, 18);
        LocalDate date8 = LocalDate.of(2027, 6, 18);

        //Bookings
        Booking booking1 = new Booking(room1, guest1, date1, date2);
        System.out.println(booking1); //Showing toString() of booking class
        System.out.println("---------------------------------------------");
        //Has bad dates, will not be able to add this to Hotel
        Booking booking2 = new Booking(room2, guest2, date4, date3);
        //Overlaps with booking1
        Booking booking3 = new Booking(room1, guest3, date7, date5);

        //Adding rooms
        hKey.addRoom(room1);
        hKey.addRoom(room2);
        hKey.addRoom(room3);
        //Showing that you cant add the same room twice
        hKey.addRoom(room1);
        System.out.println("---------------------------------------------");

        //Adding bookings
        hKey.addBooking(booking1);
        //Will not add because bad dates
        hKey.addBooking(booking2);
        //Will not add because overlaps with booking1
        hKey.addBooking(booking3);
        //Adding with other parameters, not just with booking class
        hKey.addBooking(room3, guest4, date6, date8);
        System.out.println("---------------------------------------------");

        //Room list & Booking list
        hKey.displayRooms();
        System.out.println("---------------------------------------------");
        hKey.displayBookings();

        //Remove booking
        hKey.removeBooking(booking1); //Remove by booking object
        hKey.removeBooking(hKey.getBookings().getLast().getID()); //Remove by ID

        //Showing they were removed:
        System.out.println("---------------------------------------------");
        hKey.displayBookings();

    }
}
