package Exceptions;

public class InvalidInputDatesException extends Exception{

    public InvalidInputDatesException() { //super() gets called automatically, doesn't need to be written
        super("Bad dates!");
    }

    public InvalidInputDatesException(String message) {
        super(message);
    }
}
