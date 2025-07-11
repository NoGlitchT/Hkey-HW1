package Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RoomService extends HotelService {

    private static String serviceDescription = "This service brings food and beverages.";
    private static BigDecimal baseCost = BigDecimal.valueOf(20.0);
    public RoomService(LocalDate start, LocalDate end) {
        super(start, end);
        totalCost = calculateCost();
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }


    public static void setServiceDescription(String newDescription) {
        serviceDescription = newDescription;
    }

    @Override
    public BigDecimal calculateCost() {
        return BigDecimal.valueOf(getPeriod())
                .multiply(getBaseCost());
    }

    @Override
    public String toString() {
        return "Hotel.Room service:\n" + "Service ID: " + getID() +
                "\nDescription:\n" + getServiceDescription() +
                "\nBase cost: " + getBaseCost() +
                "\nTotal:" + totalCost;
    }

    @Override
    public boolean isAvailable(List<HotelService> services, LocalDate start, LocalDate end) {
        //For every service in arraylist
        for (HotelService current : services) {
            if (current.equals(this)) {
                    return false; //Service already used.
            }
        }
        //Else, return true
        return true;
    }
}

