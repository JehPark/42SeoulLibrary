package com.seoul.library.jeheon.exception;

public class NotEnoughBookException extends RuntimeException{
    public NotEnoughBookException() {
        super();
    }

    public NotEnoughBookException(String message) {
        super(message);
    }

    public NotEnoughBookException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughBookException(Throwable cause) {
        super(cause);
    }
}
