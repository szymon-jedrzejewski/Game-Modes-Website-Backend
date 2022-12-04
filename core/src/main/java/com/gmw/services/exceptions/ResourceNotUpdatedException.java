package com.gmw.services.exceptions;

public class ResourceNotUpdatedException extends Exception {
    public ResourceNotUpdatedException() {
    }

    public ResourceNotUpdatedException(String message) {
        super(message);
    }

    public ResourceNotUpdatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotUpdatedException(Throwable cause) {
        super(cause);
    }

    public ResourceNotUpdatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
