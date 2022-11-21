package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.game.DBGameReadService;
import com.gmw.services.game.DBGameService;
import org.springframework.http.HttpStatus;

import java.util.List;

public class FindAllGamesActivity extends Activity<List<ExistingGameTO>> {
    @Override
    protected List<ExistingGameTO> realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameReadService service = serviceManager.getDbGameReadService();

        status = HttpStatus.OK;

        return service.obtainAllGames();
    }
}
