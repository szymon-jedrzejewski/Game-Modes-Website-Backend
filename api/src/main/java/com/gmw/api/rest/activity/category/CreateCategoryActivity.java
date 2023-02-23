package com.gmw.api.rest.activity.category;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.security.JwtUtils;
import com.gmw.api.rest.utils.PermissionChecker;
import com.gmw.category.tos.NewCategoryTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateCategoryActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final NewCategoryTO category;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {

            if (PermissionChecker.isAdmin(token))
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
