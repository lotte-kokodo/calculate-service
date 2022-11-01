package shop.kokodo.calculateservice.exception;

public class CalculateListNotFoundException extends RuntimeException {

    public CalculateListNotFoundException() {
        super();
    }

    public CalculateListNotFoundException(String message) {
        super(message);
    }

    public CalculateListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculateListNotFoundException(Throwable cause) {
        super(cause);
    }
}
