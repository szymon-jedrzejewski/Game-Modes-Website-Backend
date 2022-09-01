package com.gmw.api.rest.activity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Activity<T> {

    protected HttpStatus status;

    protected abstract T realExecute();

    public ResponseEntity<T> execute() {
        T result = realExecute();
        return new ResponseEntity<>(result, status);
    }
}
