package Services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SpaTreatment extends HotelService {

    private static String serviceDescription = "This service gives you a nice spa day.";
    private static BigDecimal baseCost = BigDecimal.valueOf(40.0);
    public SpaTreatment (LocalDate start, LocalDate end) {
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
        return "Spa Treatment:\n" + "Service ID: " + getID() +
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
