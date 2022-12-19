package com.gmw.services.exceptions;

public class ServiceManagerFactoryException extends Exception {
    public ServiceManagerFactoryException() {
    }

    public ServiceManagerFactoryException(String message) {
        super(message);
    }

    public ServiceManagerFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceManagerFactoryException(Throwable cause) {
        super(cause);
    }

    public ServiceManagerFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
