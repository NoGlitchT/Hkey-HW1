package Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import Interfaces.Bookable;
import Interfaces.Chargeable;

public abstract class HotelService implements Bookable<HotelService>, Chargeable {
    protected final UUID ID;
    protected final LocalDate start;
    protected final LocalDate end;
    protected BigDecimal totalCost;
    protected int period; //How long/how many times the service has been used over the guest's stay

    protected HotelService(LocalDate start, LocalDate end) {
        ID = UUID.randomUUID();
        this.start = start;
        this.end = end;
        period = start.until(end)
                .getDays();
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public double getPeriod() {
        return period;
    }

    public UUID getID() {
        return ID;
    }
    public BigDecimal getTotalCost() {
        return totalCost;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        //Cast to HotelService, check if ID is same
        HotelService hotelService = (HotelService) obj;
        return getID() == hotelService.getID();
    }
}
