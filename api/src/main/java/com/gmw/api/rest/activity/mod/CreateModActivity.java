package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.security.JwtUtils;
import com.gmw.fieldvalue.tos.NewFieldValueTO;
import com.gmw.mod.tos.NewModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateModActivity extends Activity<Void> {
    private final NewModTO newModTO;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotFoundException,
            ResourceNotDeletedException, ResourceNotCreatedException, ResourceNotUpdatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            Long modId = serviceManager.getDbModService().createMod(newModTO);

            for (NewFieldValueTO fieldValueTO : newModTO.getFieldsValues()) {
                fieldValueTO.setModId(modId);
                serviceManager.getDbFieldValueService().createFieldValue(fieldValueTO);
            }
        } catch (Exception e) {
            throw new ResourceNotCreatedException(e);
        }
        return null;
    }
}
