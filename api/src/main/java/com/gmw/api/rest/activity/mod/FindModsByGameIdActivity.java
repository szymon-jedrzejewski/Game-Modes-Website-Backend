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
public class FindModsByGameIdActivity extends Activity<List<ExistingModTO>> {
    private final Long gameId;

    @Override
    protected List<ExistingModTO> realExecute() throws ResourceNotFoundException {
        try(ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            status = HttpStatus.OK;
            return serviceManager.getDbModReadService().findModsByGameId(gameId);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
    }
}
