package com.gmw.services.exceptions;

public class ResourceNotDeletedException extends Exception {
    public ResourceNotDeletedException() {
    }

    public ResourceNotDeletedException(String message) {
        super(message);
    }

    public ResourceNotDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotDeletedException(Throwable cause) {
        super(cause);
    }

    public ResourceNotDeletedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
