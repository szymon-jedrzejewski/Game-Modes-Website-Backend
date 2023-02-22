package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.field.tos.ExistingFieldTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.view.DBViewService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class DeleteViewActivity extends Activity<Void> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Long viewId;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotDeletedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                DBFieldService fieldService = serviceManager.getDbFieldService();
                DBViewService viewService = serviceManager.getDbViewService();

                List<Long> fieldsIds = fieldService
                        .obtainFieldsByViewId(viewId)
                        .stream()
                        .map(ExistingFieldTO::getId)
                        .toList();

                for (Long id : fieldsIds) {
                    fieldService.deleteField(id);
                }
                viewService.deleteView(viewId);

                status = HttpStatus.OK;
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            throw new ResourceNotDeletedException();
        }
        return null;
    }
}
