package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.field.tos.NewFieldTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.view.DBViewService;
import com.gmw.view.tos.NewViewTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateViewActivity extends Activity<Void> {

    private final NewViewTO newView;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                DBViewService service = serviceManager.getDbViewService();
                DBFieldService fieldService = serviceManager.getDbFieldService();
                Long viewId = service.createView(newView);

                for (NewFieldTO field : newView.getFields()) {
                    fieldService.createField(field, viewId);
                }

                status = HttpStatus.CREATED;
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            throw new ResourceNotCreatedException();
        }
        return null;
    }
}
