package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.field.tos.ExistingFieldTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.field.DBFieldService;
import com.gmw.view.tos.ExistingViewTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateViewActivity extends Activity<Void> {

    private final ExistingViewTO view;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                DBFieldService service = serviceManager.getDbFieldService();

                Long viewId = serviceManager.getDbViewReadService().obtainViewById(view.getId()).getId();

                for (ExistingFieldTO field : view.getFields()) {
                    service.updateField(field, viewId);
                }

                status = HttpStatus.OK;
            } else {
                setForbidden();
            }
        } catch (Exception e) {
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
