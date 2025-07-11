package Hotel;

import Exceptions.RoomUnavailableException;
import Interfaces.Bookable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class Room implements Bookable<Booking>{
    public enum RoomType {
        //Each room type has a rate
        Deluxe(200.0), Suite (250.0), Standard(170.0);
        private final BigDecimal rate;
        //Rate initialized in constructor
        RoomType(double rate) {
            this.rate = new BigDecimal(rate);
        }
        public BigDecimal getRate() {
            return rate;
        }
        //Returns what type of room it is
        public String toString() {
            return switch (this) {
                case Suite -> "Suite";
                case Deluxe -> "Deluxe Hotel.Room";
                case Standard -> "Standard Hotel.Room";
            };
        }
    }

    private final UUID ID;
    private final RoomType type;
    public Room() {
        ID = UUID.randomUUID();
        type = RoomType.Standard;

    }
    public Room(RoomType type) {
        ID = UUID.randomUUID();
        this.type = type;
    }

    public UUID getID() {
        return ID;
    }
    public RoomType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Hotel.Room ID: " + getID() +
                "\nHotel.Room type: " + getType().toString() +
                "\nNightly rate: " + getType().getRate();

    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        //Cast to room, check if ID is same
        Room room = (Room) obj;
        return getID() == room.getID();

    }
    @Override
    public boolean isAvailable(List<Booking> bookings, LocalDate start, LocalDate end) throws RoomUnavailableException {
        //For every room in arraylist
        for (Booking b : bookings) {
            if (b.getRoom().equals(this)) {
                //Do dates overlap?
                if (start.isBefore(b.getEnd()) && end.isAfter(b.getStart())) {
                    throw new RoomUnavailableException("Date taken!");
                }
                //Are dates the same?
                if (start.equals(b.getStart()) && end.equals(b.getEnd())) {
                    throw new RoomUnavailableException("Date taken!");
                }
            }
        }
        //Else, return true
        return true;
    }
}
