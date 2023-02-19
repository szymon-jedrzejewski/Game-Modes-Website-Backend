package com.gmw.api.rest.activity.rating;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ObtainRatingForModActivity extends Activity<Double> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Long modId;

    @Override
    protected Double realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()){
            status = HttpStatus.OK;
            return serviceManager.getDbRatingService().obtainRatingForMod(modId);
        } catch (Exception e) {
            LOGGER.error("Cannot obtain rating for mod: " + modId);
            throw new ResourceNotFoundException(e);
        }
    }
}
