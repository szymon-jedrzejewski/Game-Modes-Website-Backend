package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class FindAllModsActivity extends Activity<List<ExistingModTO>> {

    @Override
    protected List<ExistingModTO> realExecute() throws ResourceNotFoundException {
        try(ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            status = HttpStatus.OK;
            return serviceManager.getDbModReadService().findAllMods();
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
    }
}
