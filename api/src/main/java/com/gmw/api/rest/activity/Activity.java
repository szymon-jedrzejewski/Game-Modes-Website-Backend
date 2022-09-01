package com.gmw.api.rest.activity;

import com.gmw.viewbuilder.services.ServiceManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Activity<T> {

    protected HttpStatus status;

    private ServiceManager serviceManager;

    protected abstract T realExecute();

    public ResponseEntity<T> execute() {
        T result = realExecute();
        return new ResponseEntity<>(result, status);
    }

    protected ServiceManager getServiceManager() {
        return new ServiceManager();
    }
}
