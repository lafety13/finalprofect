package ua.booking.entities;

public class AuthException extends Exception {
    public AuthException() {
        super();
    }

    public AuthException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace();
    }
}
