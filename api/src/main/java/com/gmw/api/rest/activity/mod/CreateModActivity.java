package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.fieldvalue.tos.NewFieldValueTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateModActivity extends Activity<Void> {
    private final NewModTO newModTO;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotFoundException,
            ResourceNotDeletedException, ResourceNotCreatedException, ResourceNotUpdatedException {

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                Long modId = serviceManager.getDbModService().createMod(newModTO);

                for (NewFieldValueTO fieldValueTO : newModTO.getFieldsValues()) {
                    fieldValueTO.setModId(modId);
                    serviceManager.getDbFieldValueService().createFieldValue(fieldValueTO);
                }
            } else {
                setForbidden();
            }
        } catch (Exception e) {
            throw new ResourceNotCreatedException(e);
        }
        return null;
    }
}
