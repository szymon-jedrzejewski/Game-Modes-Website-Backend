package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.JwtUtils;
import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.fieldvalues.DBFieldValueService;
import com.gmw.services.mod.DBModService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateModActivity extends Activity<Void> {
    private final ExistingModTO existingModTO;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBModService service = serviceManager.getDbModService();
            Long userIdFromMod = service.findModById(existingModTO.getId()).getUserId();

            if (userIdFromMod.equals(JwtUtils.extractUserId(token))) {
                service.updateMod(existingModTO);
                DBFieldValueService fieldValueService = serviceManager.getDbFieldValueService();

                for (ExistingFieldValueTO fieldsValue : existingModTO.getFieldsValues()) {
                    fieldValueService.updateFieldValue(fieldsValue);
                }
            } else {
                throw new PermissionDeniedException();
            }

            status = HttpStatus.OK;
        } catch (Exception e) {
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
