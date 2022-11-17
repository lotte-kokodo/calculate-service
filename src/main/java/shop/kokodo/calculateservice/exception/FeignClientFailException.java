package shop.kokodo.calculateservice.exception;

public class FeignClientFailException extends RuntimeException{
    public FeignClientFailException() {
    }

    public FeignClientFailException(String message) {
        super(message);
    }

    public FeignClientFailException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignClientFailException(Throwable cause) {
        super(cause);
    }

    public FeignClientFailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
