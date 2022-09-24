package com.gmw.exceptions;

public class SqlQueryGeneratorException extends Exception{

    public SqlQueryGeneratorException() {
    }

    public SqlQueryGeneratorException(String message) {
        super(message);
    }

    public SqlQueryGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
