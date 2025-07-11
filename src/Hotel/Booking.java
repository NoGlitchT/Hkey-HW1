package Hotel;

import Exceptions.InvalidInputDatesException;
import Interfaces.Chargeable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Booking implements Chargeable {
    private final UUID ID;
    private final Room room;
    private final Guest guest;
    private LocalDate start;
    private LocalDate end;
    private int period;
    private BigDecimal cost;

    public Booking(Room room, Guest guest, LocalDate start, LocalDate end) {
        this.ID = UUID.randomUUID();
        this.room = room;
        this.guest = guest;
        try {
            updateDates(start, end);
        } catch (InvalidInputDatesException e) {
            System.out.println(e.getMessage());
        }

    }
    private int calculatePeriod() {
        return start.until(end)
                .getDays();
    }
    @Override
    public BigDecimal calculateCost() {
        return BigDecimal.valueOf(period)
                .multiply(room.getType().getRate());
    }
    public void updateDates(LocalDate start, LocalDate end) throws InvalidInputDatesException {
        if (start.isAfter(end)) {
            throw new InvalidInputDatesException("End date is before start date!");
        }
        this.end = end;
        this.start = start;
        this.period = calculatePeriod();
        this.cost = calculateCost();
    }

    public UUID getID() {
        return ID;
    }
    public Room getRoom() {
        return room;
    }
    public Guest getGuest() {
        return guest;
    }
    public int getPeriod() {
        return period;
    }
    public LocalDate getStart() {
        return start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd . MM . yyyy");
        return "Hotel.Booking reservation details...\nHotel.Booking ID: " + getID() +
                "\nHotel.Room details:\n" + getRoom().toString() +
                "\nHotel.Guest:\n" + getGuest().toString() +
                "\nStart & End Date:\n" + getStart().format(format) + " " + getEnd().format(format) +
                "\nPeriod: " + getPeriod() + " days." +
                "\nTotal cost: " + getCost() + "$";

    }
    @Override
    public boolean equals(Object obj) {
        //Is it the same reference?
        if (obj == this) return true;
        //Is it null OR is it a different class?
        if (obj == null || getClass() != obj.getClass()) return false;

        //Are the ID's the same?
        Booking newBooking = (Booking) obj;
        return getID() == newBooking.getID();
    }


}
