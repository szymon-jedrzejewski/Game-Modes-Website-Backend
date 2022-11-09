package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.viewbuilder.services.ServiceManager;
import com.gmw.viewbuilder.services.SqlServiceManager;
import com.gmw.viewbuilder.services.game.DBGameReadService;
import com.gmw.viewbuilder.tos.ExistingGameTO;
import org.springframework.http.HttpStatus;

public class FindGameByIdActivity extends Activity<ExistingGameTO> {

    private final Long id;

    public FindGameByIdActivity(Long id) {
        this.id = id;
    }

    @Override
    protected ExistingGameTO realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameReadService service = serviceManager.getDbGameReadService();
        status = HttpStatus.OK;
        return service.obtainGameById(id);
    }
}
