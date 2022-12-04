package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.game.DBGameService;
import com.gmw.game.tos.ExistingGameTO;
import org.springframework.http.HttpStatus;

public class UpdateGameActivity extends Activity<Void> {

    private final ExistingGameTO game;

    public UpdateGameActivity(ExistingGameTO game) {
        this.game = game;
    }

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameService service = serviceManager.getDbGameService();

        service.updateGame(game);
        status = HttpStatus.OK;
        return null;
    }
}
