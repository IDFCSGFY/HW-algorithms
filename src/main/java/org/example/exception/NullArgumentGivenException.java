package org.example.exception;

public class NullArgumentGivenException extends RuntimeException{
    public NullArgumentGivenException() {
    }

    public NullArgumentGivenException(String message) {
        super(message);
    }

    public NullArgumentGivenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullArgumentGivenException(Throwable cause) {
        super(cause);
    }

    public NullArgumentGivenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
