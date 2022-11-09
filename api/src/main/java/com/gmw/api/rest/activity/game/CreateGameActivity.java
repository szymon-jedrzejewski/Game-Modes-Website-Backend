package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.viewbuilder.services.ServiceManager;
import com.gmw.viewbuilder.services.SqlServiceManager;
import com.gmw.viewbuilder.services.game.DBGameService;
import com.gmw.viewbuilder.tos.NewGameTO;
import org.springframework.http.HttpStatus;

public class CreateGameActivity extends Activity<Void> {

    private final NewGameTO newGameTO;

    public CreateGameActivity(NewGameTO newGameTO) {
        this.newGameTO = newGameTO;
    }

    @Override
    protected Void realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameService service = serviceManager.getDbGameService();
        service.createGame(newGameTO);
        status = HttpStatus.OK;
        return null;
    }
}
