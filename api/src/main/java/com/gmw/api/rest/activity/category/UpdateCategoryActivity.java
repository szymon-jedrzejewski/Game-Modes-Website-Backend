package com.gmw.api.rest.activity.category;

import com.gmw.api.rest.activity.Activity;
import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateCategoryActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ExistingCategoryTO category;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            serviceManager.getDbCategoryService().updateCategory(category);
            status = HttpStatus.OK;
        } catch (Exception e) {
            LOGGER.error("Can not update comments with id: " + category.getId());
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
