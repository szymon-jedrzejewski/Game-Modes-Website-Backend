package com.gmw.exceptions;

public class SqlQueryUtilityException extends Exception{

    public SqlQueryUtilityException() {
    }

    public SqlQueryUtilityException(String message) {
        super(message);
    }

    public SqlQueryUtilityException(String message, Throwable cause) {
        super(message, cause);
    }
}
