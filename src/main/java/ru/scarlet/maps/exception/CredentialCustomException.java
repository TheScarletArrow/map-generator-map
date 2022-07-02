package ru.scarlet.maps.exception;

public class CredentialCustomException extends RuntimeException {
    public CredentialCustomException() {
        System.out.println(12321);
    }

    public CredentialCustomException(String message) {
        super(message);
    }

    public CredentialCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CredentialCustomException(Throwable cause) {
        super(cause);
    }

    public CredentialCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
