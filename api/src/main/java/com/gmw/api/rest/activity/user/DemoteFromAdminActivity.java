package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.tos.RoleChangeDTO;
import com.gmw.api.rest.utils.JwtUtils;
import com.gmw.api.rest.utils.PermissionChecker;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.user.DBUserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DemoteFromAdminActivity extends Activity<Void> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final RoleChangeDTO roleChangeDTO;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotFoundException, ResourceNotUpdatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            status = HttpStatus.OK;
            DBUserService service = serviceManager.getDbUserService();

            if (PermissionChecker.isAdmin(token)) {
                service.demoteFromAdmin(service.obtainUserById(roleChangeDTO.userToPromote()));
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            LOGGER.error("Cannot obtain user with email!");
            throw new ResourceNotFoundException(e);
        }

        return null;
    }
}
