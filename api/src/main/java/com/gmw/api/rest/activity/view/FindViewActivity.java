package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.field.DBFieldReadService;
import com.gmw.services.view.DBViewReadService;
import com.gmw.view.tos.ExistingViewTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class FindViewActivity extends Activity<ExistingViewTO> {

    private final Long gameId;
    private final Long userId;

    @Override
    protected ExistingViewTO realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)){
                DBViewReadService service = serviceManager.getDbViewReadService();
                DBFieldReadService fieldReadService = serviceManager.getDbFieldReadService();

                status = HttpStatus.OK;

                ExistingViewTO existingViewTO = service.obtainViewByGameId(gameId);
                existingViewTO.setFields(fieldReadService.obtainFieldsByViewId(existingViewTO.getId()));

                return existingViewTO;
            } else {
                setForbidden();
                throw new ResourceNotFoundException();
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
