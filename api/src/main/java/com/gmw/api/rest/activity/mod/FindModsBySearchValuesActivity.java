package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.fieldvalue.tos.SearchFieldValue;
import com.gmw.mod.tos.ExistingModTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FindModsBySearchValuesActivity extends Activity<List<ExistingModTO>> {
    private final List<SearchFieldValue> searchFieldValues;

    @Override
    protected List<ExistingModTO> realExecute() throws ResourceNotFoundException {
        try(ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            List<Long> modIds = serviceManager.getDbFieldValueReadService().obtainModsIdsBySearchValues(searchFieldValues);

            return serviceManager.getDbModReadService().findModsByIds(modIds);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e);
        }
    }
}
