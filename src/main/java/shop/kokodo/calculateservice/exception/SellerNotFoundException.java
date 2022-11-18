package shop.kokodo.calculateservice.exception;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException() {
    }

    public SellerNotFoundException(String message) {
        super(message);
    }

    public SellerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SellerNotFoundException(Throwable cause) {
        super(cause);
    }

    public SellerNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
