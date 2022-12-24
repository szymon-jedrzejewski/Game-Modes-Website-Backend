package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.field.DBFieldReadService;
import com.gmw.services.view.DBViewReadService;
import com.gmw.view.tos.ExistingViewTO;
import org.springframework.http.HttpStatus;

public class FindViewActivity extends Activity<ExistingViewTO> {

    private final Long gameId;

    public FindViewActivity(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    protected ExistingViewTO realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBViewReadService service = serviceManager.getDbViewReadService();
            DBFieldReadService fieldReadService = serviceManager.getDbFieldReadService();

            status = HttpStatus.OK;

            ExistingViewTO existingViewTO = service.obtainViewByGameId(gameId);
            existingViewTO.setFields(fieldReadService.obtainFieldsByViewId(existingViewTO.getId()));

            return existingViewTO;
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
