package com.gmw.exceptions;

public class SqlRepositoryException extends Throwable {
    public SqlRepositoryException() {
    }

    public SqlRepositoryException(String message) {
        super(message);
    }

    public SqlRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
