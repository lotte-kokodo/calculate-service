package shop.kokodo.calculateservice.exception;

public class SellerFinanceNotFoundException extends RuntimeException {
    public SellerFinanceNotFoundException() {
    }

    public SellerFinanceNotFoundException(String message) {
        super(message);
    }

    public SellerFinanceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public SellerFinanceNotFoundException(Throwable cause) {
        super(cause);
    }

    public SellerFinanceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
