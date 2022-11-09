package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.viewbuilder.services.ServiceManager;
import com.gmw.viewbuilder.services.SqlServiceManager;
import com.gmw.viewbuilder.services.game.DBGameService;
import com.gmw.viewbuilder.tos.ExistingGameTO;
import org.springframework.http.HttpStatus;

public class UpdateGameActivity extends Activity<Void> {

    private final ExistingGameTO game;

    public UpdateGameActivity(ExistingGameTO game) {
        this.game = game;
    }

    @Override
    protected Void realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameService service = serviceManager.getDbGameService();
        service.updateGame(game);
        status = HttpStatus.OK;
        return null;
    }
}
