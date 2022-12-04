package com.gmw.services.exceptions;

public class ResourceNotCreatedException extends Exception {
    public ResourceNotCreatedException() {
    }

    public ResourceNotCreatedException(String message) {
        super(message);
    }

    public ResourceNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotCreatedException(Throwable cause) {
        super(cause);
    }

    public ResourceNotCreatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
