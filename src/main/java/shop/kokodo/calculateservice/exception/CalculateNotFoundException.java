package shop.kokodo.calculateservice.exception;

public class CalculateNotFoundException extends RuntimeException {
    public CalculateNotFoundException() {
    }

    public CalculateNotFoundException(String message) {
        super(message);
    }

    public CalculateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculateNotFoundException(Throwable cause) {
        super(cause);
    }

    public CalculateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
