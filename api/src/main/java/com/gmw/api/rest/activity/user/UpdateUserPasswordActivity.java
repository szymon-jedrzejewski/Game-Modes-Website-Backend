package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.activity.user.tos.ChangePasswordTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.user.DBUserService;
import com.gmw.user.tos.ExistingUserTO;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateUserPasswordActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ChangePasswordTO passwordTO;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {

            DBUserService service = serviceManager.getDbUserService();
            ExistingUserTO existingUserTO = service.obtainUserById(passwordTO.getUserId());
            existingUserTO.setPassword(passwordTO.getPassword());

            service.updateUser(existingUserTO);
            status = HttpStatus.OK;
        } catch (Exception e) {
            LOGGER.error("Can not update password for user with id: " + passwordTO.getUserId());
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
