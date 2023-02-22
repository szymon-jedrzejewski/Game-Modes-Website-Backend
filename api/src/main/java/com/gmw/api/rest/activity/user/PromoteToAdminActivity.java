package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.activity.user.tos.RoleChangeDTO;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.user.DBUserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class PromoteToAdminActivity extends Activity<Void> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final RoleChangeDTO roleChangeDTO;

    @Override
    protected Void realExecute() throws ResourceNotFoundException, ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()){
            status = HttpStatus.OK;
            DBUserService service = serviceManager.getDbUserService();

            if (RoleChecker.isAdmin(serviceManager, roleChangeDTO.adminId())) {
                service.promoteToAdmin(service.obtainUserById(roleChangeDTO.userToPromote()));
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
