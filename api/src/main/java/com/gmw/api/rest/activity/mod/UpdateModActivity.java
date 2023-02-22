package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.fieldvalues.DBFieldValueService;
import com.gmw.services.mod.DBModService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateModActivity extends Activity<Void> {
    private final ExistingModTO existingModTO;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBModService service = serviceManager.getDbModService();
            Long userIdFromMod = service.findModById(existingModTO.getId()).getUserId();

            if (userIdFromMod.equals(userId)) {
                service.updateMod(existingModTO);
                DBFieldValueService fieldValueService = serviceManager.getDbFieldValueService();

                for (ExistingFieldValueTO fieldsValue : existingModTO.getFieldsValues()) {
                    fieldValueService.updateFieldValue(fieldsValue);
                }
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
