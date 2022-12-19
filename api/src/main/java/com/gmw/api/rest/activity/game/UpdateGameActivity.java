package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.exceptions.ServiceManagerFactoryException;
import com.gmw.services.game.DBGameService;
import org.springframework.http.HttpStatus;

public class UpdateGameActivity extends Activity<Void> {

    private final ExistingGameTO game;

    public UpdateGameActivity(ExistingGameTO game) {
        this.game = game;
    }

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try {
            ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager();
            DBGameService service = serviceManager.getDbGameService();

            service.updateGame(game);
            status = HttpStatus.OK;
        } catch (ServiceManagerFactoryException e) {
            status = HttpStatus.CONFLICT;
        }

        return null;
    }
}
