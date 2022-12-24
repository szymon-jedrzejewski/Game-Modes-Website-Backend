package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.fieldvalue.tos.NewFieldValueTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.*;

public class CreateModActivity extends Activity<Void> {
    private final NewModTO newModTO;

    public CreateModActivity(NewModTO newModTO) {
        this.newModTO = newModTO;
    }

    @Override
    protected Void realExecute() throws ResourceNotFoundException,
            ResourceNotDeletedException, ResourceNotCreatedException, ResourceNotUpdatedException {

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            Long modId = serviceManager.getDbModService().createMod(newModTO);

            for (NewFieldValueTO fieldValueTO : newModTO.getFieldsValues())
            {
                fieldValueTO.setModId(modId);
                serviceManager.getDbFieldValueService().createFieldValue(fieldValueTO);
            }
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
        return null;
    }
}
