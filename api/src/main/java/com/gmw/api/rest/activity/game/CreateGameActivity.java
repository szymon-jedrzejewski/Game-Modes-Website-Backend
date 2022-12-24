package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.game.tos.NewGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.game.DBGameService;
import org.springframework.http.HttpStatus;

public class CreateGameActivity extends Activity<Void> {

    private final NewGameTO newGameTO;

    public CreateGameActivity(NewGameTO newGameTO) {
        this.newGameTO = newGameTO;
    }

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBGameService service = serviceManager.getDbGameService();
            status = HttpStatus.CREATED;
            service.createGame(newGameTO);
        } catch (Exception e) {
            throw new ResourceNotCreatedException();
        }

        return null;
    }
}
