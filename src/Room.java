import java.math.BigDecimal;
import java.util.UUID;

public class Room {
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
                case Deluxe -> "Deluxe Room";
                case Standard -> "Standard Room";
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
        return "Room ID: " + getID() +
                "\nRoom type: " + getType().toString() +
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
}
