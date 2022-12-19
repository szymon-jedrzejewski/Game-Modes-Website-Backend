package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.ServiceManagerFactoryException;
import com.gmw.services.game.DBGameReadService;
import org.springframework.http.HttpStatus;

import java.util.List;

public class FindAllGamesActivity extends Activity<List<ExistingGameTO>> {
    @Override
    protected List<ExistingGameTO> realExecute() throws ResourceNotFoundException {
        try {
            ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager();
            DBGameReadService service = serviceManager.getDbGameReadService();
            status = HttpStatus.OK;
            return service.obtainAllGames();
        } catch (ServiceManagerFactoryException e) {
            status = HttpStatus.NOT_FOUND;
        }
        return null;
    }
}
