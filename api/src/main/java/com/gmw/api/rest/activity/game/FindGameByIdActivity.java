package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.game.DBGameReadService;
import com.gmw.game.tos.ExistingGameTO;
import org.springframework.http.HttpStatus;

public class FindGameByIdActivity extends Activity<ExistingGameTO> {

    private final Long id;

    public FindGameByIdActivity(Long id) {
        this.id = id;
    }

    @Override
    protected ExistingGameTO realExecute() throws ResourceNotFoundException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBGameReadService service = serviceManager.getDbGameReadService();
        status = HttpStatus.OK;
        return service.obtainGameById(id);
    }
}
