package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ServiceManagerFactoryException;
import com.gmw.services.game.DBGameService;
import org.springframework.http.HttpStatus;

public class DeleteGameActivity extends Activity<Void> {

    private final Long id;

    public DeleteGameActivity(Long id) {
        this.id = id;
    }

    @Override
    protected Void realExecute() throws ResourceNotDeletedException {
        try {
            ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager();
            DBGameService service = serviceManager.getDbGameService();
            service.deleteGame(id);
            status = HttpStatus.OK;
        } catch (ServiceManagerFactoryException e) {
            status = HttpStatus.CONFLICT;
        }
        return null;
    }
}
