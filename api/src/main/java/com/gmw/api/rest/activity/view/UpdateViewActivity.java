package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.field.DBFieldService;
import com.gmw.view.tos.ExistingFieldTO;
import com.gmw.view.tos.ExistingViewTO;
import org.springframework.http.HttpStatus;

public class UpdateViewActivity extends Activity<Void> {

    private final ExistingViewTO view;

    public UpdateViewActivity(ExistingViewTO view) {
        this.view = view;
    }

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBFieldService service = serviceManager.getDbFieldService();

        try {
            Long viewId = serviceManager.getDbViewBuilderReadService().obtainViewById(view.getId()).getId();

            for (ExistingFieldTO field : view.getFields())
            {
                service.updateField(field, viewId);
            }
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotUpdatedException(e);
        }

        status = HttpStatus.OK;
        return null;
    }
}
