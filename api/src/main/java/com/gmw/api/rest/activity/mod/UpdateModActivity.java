package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.fieldvalues.DBFieldValueService;

public class UpdateModActivity extends Activity<Void> {
    private final ExistingModTO existingModTO;

    public UpdateModActivity(ExistingModTO existingModTO) {
        this.existingModTO = existingModTO;
    }

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            serviceManager.getDbModService().updateMod(existingModTO);

            DBFieldValueService fieldValueService = serviceManager.getDbFieldValueService();

            for (ExistingFieldValueTO fieldsValue : existingModTO.getFieldsValues()) {
                fieldValueService.updateFieldValue(fieldsValue);
            }

        } catch (Exception e) {
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
