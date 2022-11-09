package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.game.DBGameReadService;
import com.gmw.game.tos.ExistingGameTO;
import org.springframework.http.HttpStatus;

public class FindGameByNameActivity extends Activity<ExistingGameTO> {

    private final String name;

    public FindGameByNameActivity(String name) {
        this.name = name;
    }

    @Override
    protected ExistingGameTO realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameReadService service = serviceManager.getDbGameReadService();
        status = HttpStatus.OK;
        return service.obtainGameByName(name);
    }
}
