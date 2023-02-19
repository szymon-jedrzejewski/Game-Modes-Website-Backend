package com.gmw.api.rest.activity.category;

import com.gmw.api.rest.activity.Activity;
import com.gmw.category.tos.ExistingCategoryTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class FindAllCategoriesActivity extends Activity<List<ExistingCategoryTO>> {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected List<ExistingCategoryTO> realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()){
            status = HttpStatus.OK;
            return serviceManager.getDbCategoryReadService().obtainCategories();
        } catch (Exception e) {
            LOGGER.error("Cannot obtain categories!");
            throw new ResourceNotFoundException(e);
        }
    }
}
