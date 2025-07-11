package Exceptions;

public class RoomUnavailableException extends Exception{

    public RoomUnavailableException() {
        super("Room taken!");
    }
    public RoomUnavailableException(String message) {
        super(message);
    }

}
