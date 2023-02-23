package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.security.JwtUtils;
import com.gmw.api.rest.utils.PermissionChecker;
import com.gmw.field.tos.NewFieldTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.view.DBViewService;
import com.gmw.view.tos.NewViewTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateViewActivity extends Activity<Void> {

    private final NewViewTO newView;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (PermissionChecker.isAdmin(token)) {
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
