package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.user.DBUserReadService;
import com.gmw.user.tos.ExistingUserTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequiredArgsConstructor
public class FindUserByIdActivity extends Activity<ExistingUserTO> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final Long userId;

    @Override
    protected ExistingUserTO realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBUserReadService service = serviceManager.getDbUserReadService();
            return service.obtainUserById(userId);
        } catch (Exception e) {
            LOGGER.error("Cannot save new user!");
            throw new ResourceNotFoundException(e);
        }
    }
}
