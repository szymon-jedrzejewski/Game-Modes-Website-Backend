package com.gmw.api.rest.activity;

import com.gmw.services.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class Activity<T> {
    private static final Logger LOGGER = LogManager.getLogger();

    protected HttpStatus status;

    protected abstract T realExecute() throws ResourceNotFoundException, ResourceNotDeletedException,
            ResourceNotCreatedException, ResourceNotUpdatedException, UnauthorizedException, PermissionDeniedException;

    public ResponseEntity<T> execute() {
        T result = null;
        try {
            result = realExecute();
        } catch (ResourceNotDeletedException | ResourceNotCreatedException | ResourceNotUpdatedException e) {
            status = HttpStatus.CONFLICT;
        } catch (ResourceNotFoundException e) {
            status = HttpStatus.NOT_FOUND;
        } catch (UnauthorizedException e) {
            status = HttpStatus.UNAUTHORIZED;
        } catch (PermissionDeniedException e) {
            LOGGER.info("User not permitted for this action!");
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<>(result, status);
    }
}
