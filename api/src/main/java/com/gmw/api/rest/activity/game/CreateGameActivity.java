package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.game.DBGameService;
import com.gmw.game.tos.NewGameTO;
import org.springframework.http.HttpStatus;

public class CreateGameActivity extends Activity<Void> {

    private final NewGameTO newGameTO;

    public CreateGameActivity(NewGameTO newGameTO) {
        this.newGameTO = newGameTO;
    }

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameService service = serviceManager.getDbGameService();

        status = HttpStatus.CREATED;
        service.createGame(newGameTO);
        return null;
    }
}
