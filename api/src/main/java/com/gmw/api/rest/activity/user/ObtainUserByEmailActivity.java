package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.user.tos.ExistingUserTO;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ObtainUserByEmailActivity extends Activity<ExistingUserTO> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final String email;

    @Override
    protected ExistingUserTO realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()){
            status = HttpStatus.OK;
            return serviceManager.getDbUserReadService().obtainUserByEmail(email);
        } catch (Exception e) {
            LOGGER.error("Cannot obtain user with email!");
            throw new ResourceNotFoundException(e);
        }
    }
}
