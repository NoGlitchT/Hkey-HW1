package Exceptions;

public class ServiceNotFoundException extends Exception{

    public ServiceNotFoundException() {
        super("No such service!");
    }

    public ServiceNotFoundException(String message) {
        super(message);
    }
}
