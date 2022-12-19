package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ServiceManagerFactoryException;
import com.gmw.services.game.DBGameReadService;
import org.springframework.http.HttpStatus;

public class FindGameByNameActivity extends Activity<ExistingGameTO> {

    private final String name;

    public FindGameByNameActivity(String name) {
        this.name = name;
    }

    @Override
    protected ExistingGameTO realExecute() throws ResourceNotFoundException {
        try {
            ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager();
            DBGameReadService service = serviceManager.getDbGameReadService();
            status = HttpStatus.OK;
            return service.obtainGameByName(name);
        } catch (ServiceManagerFactoryException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return null;
    }
}
