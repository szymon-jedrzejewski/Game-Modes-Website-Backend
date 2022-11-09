package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.game.DBGameService;
import org.springframework.http.HttpStatus;

public class DeleteGameActivity extends Activity<Void> {

    private final Long id;

    public DeleteGameActivity(Long id) {
        this.id = id;
    }

    @Override
    protected Void realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameService service = serviceManager.getDbGameService();
        service.deleteGame(id);
        status = HttpStatus.OK;
        return null;
    }
}
