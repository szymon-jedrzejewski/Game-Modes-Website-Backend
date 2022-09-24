package com.gmw.exceptions;

public class SqlPersistenceManagerException extends Exception {
    public SqlPersistenceManagerException() {
    }

    public SqlPersistenceManagerException(String message) {
        super(message);
    }

    public SqlPersistenceManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
