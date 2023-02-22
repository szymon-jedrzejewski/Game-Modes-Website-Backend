package com.gmw.api.rest.activity.category;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.category.tos.NewCategoryTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateCategoryActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final NewCategoryTO category;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId))
            {
                serviceManager.getDbCategoryService().createCategory(category);
                status = HttpStatus.CREATED;
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            LOGGER.error("Cannot save new category!");
            throw new ResourceNotCreatedException(e);
        }
        return null;
    }
}
