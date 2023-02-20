package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.fieldvalues.DBFieldValueService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateModActivity extends Activity<Void> {
    private final ExistingModTO existingModTO;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {

            if (RoleChecker.isAdmin(serviceManager, userId)) {
                serviceManager.getDbModService().updateMod(existingModTO);

                DBFieldValueService fieldValueService = serviceManager.getDbFieldValueService();

                for (ExistingFieldValueTO fieldsValue : existingModTO.getFieldsValues()) {
                    fieldValueService.updateFieldValue(fieldsValue);
                }
            } else {
                setForbidden();
            }

        } catch (Exception e) {
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
