package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.JwtUtils;
import com.gmw.api.rest.utils.PermissionChecker;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.user.tos.ExistingUserTO;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateUserActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ExistingUserTO userTO;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (PermissionChecker.isAdmin(token)) {
                serviceManager.getDbUserService().updateUser(userTO);
                status = HttpStatus.OK;
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            LOGGER.error("Can not update user with id: " + userTO.getId());
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
