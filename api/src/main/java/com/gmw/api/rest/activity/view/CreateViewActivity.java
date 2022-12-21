package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ServiceManagerFactoryException;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.view.DBViewService;
import field.tos.NewFieldTO;
import com.gmw.view.tos.NewViewTO;
import org.springframework.http.HttpStatus;

public class CreateViewActivity extends Activity<Void> {

    private final NewViewTO newView;

    public CreateViewActivity(NewViewTO newView) {
        this.newView = newView;
    }

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
        try {
            ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager();
            DBViewService service = serviceManager.getDbViewService();
            DBFieldService fieldService = serviceManager.getDbFieldService();
            Long viewId = service.createView(newView);

            for (NewFieldTO field : newView.getFields()) {
                fieldService.createField(field, viewId);
            }

            status = HttpStatus.CREATED;
        } catch (ServiceManagerFactoryException e) {
            status = HttpStatus.CONFLICT;
        }
        return null;
    }
}
