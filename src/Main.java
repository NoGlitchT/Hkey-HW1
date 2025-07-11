import Exceptions.ServiceNotFoundException;
import Hotel.Booking;
import Hotel.Guest;
import Hotel.Room;
import Services.HotelService;
import Services.LaundryService;
import Services.RoomService;
import Services.SpaTreatment;
import Staff.Staff;
import Staff.Manager;
import Staff.HouseKeepingStaff;
import Staff.FrontDeskStaff;

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
        Guest guest1 = new Guest("Mark Twain", "MT@writernmail.com");
        Guest guest2 = new Guest("Sean Irish", "random@fakemail.com");
        Guest guest3 = new Guest("John Computer", "computerman@crazymail.com");
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
        //Has bad dates, will throw exception internally and not be created
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
        //Will not add because overlaps with booking1
        hKey.addBooking(booking3);
        //Adding with other parameters, not just with booking class
        hKey.addBooking(room3, guest4, date6, date8);
        System.out.println("---------------------------------------------");

        //Hotel.Room list & Hotel.Booking list
        hKey.displayRooms();
        System.out.println("---------------------------------------------");
        hKey.displayBookings();

        //Remove booking
        hKey.removeBooking(booking1); //Remove by booking object
        hKey.removeBooking(hKey.getBookings().getLast().getID()); //Remove by ID

        //Showing they were removed:
        System.out.println("---------------------------------------------");
        hKey.displayBookings();

        //---------------HOMEWORK 2------------------------------------------------------------------------
        System.out.println("---------------------------------------------");
        HotelService service1 = new LaundryService(date1, date2); //Random dates. Indicates how long the service is used for
        System.out.println(service1);
        //All services have base cost and description, description can be changed for whole class.
        LaundryService.setServiceDescription("This is a new description!");
        System.out.println(service1);

        HotelService service2 = new LaundryService(date8, date1); //Bad dates, won't be created.
        //Each instance of a service is a booking, to be added to a paricular guest booking's total
        HotelService service3 = new RoomService(date1, date2);
        HotelService service4 = new SpaTreatment(date1, date2);
        System.out.println(service3);
        System.out.println(service4);


        //If service type didnt exist, would throw exception
        try {
            hKey.addService(service1);
        } catch (ServiceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            hKey.addService(service3);
        } catch (ServiceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            hKey.addService(service4);
        } catch (ServiceNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------------------------------------------");
        hKey.displayServices();

        //Staff
        System.out.println("---------------------------------------------");
        Staff manager = new Manager("Bob Ross", "Sanitation department");
        Staff housekeeper = new HouseKeepingStaff("Emily Cleaner");
        FrontDeskStaff frontDesk = new FrontDeskStaff("John Greeter");

        manager.performDuty();
        housekeeper.performDuty();

        //Perform duty for front desk calls greetGuest(), but there is also greetGuest(String)
        frontDesk.performDuty();
        frontDesk.greetGuest(guest1.getFullName());

        System.out.println("---------------------------------------------");
        hKey.addStaff(manager);
        hKey.addStaff(housekeeper);
        hKey.addStaff(frontDesk);
        System.out.println("---------------------------------------------");
        //All workers do work, also showing worker list
        hKey.displayStaff();
        System.out.println("---------------------------------------------");
        hKey.workStaff();

        System.out.println("---------------------------------------------");
        //The following method can use any list of services, which would be added together, in this case i used all
        //that the class had
        System.out.println("Current total for Mr.Twain is " + hKey.bookingTotal(booking1, hKey.getServices()));

    }
}
