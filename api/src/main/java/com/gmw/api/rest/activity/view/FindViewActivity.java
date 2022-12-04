package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.view.DBViewBuilderReadService;
import com.gmw.view.tos.ExistingViewTO;
import org.springframework.http.HttpStatus;

public class FindViewActivity extends Activity<ExistingViewTO> {

    private final Long gameId;

    public FindViewActivity(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    protected ExistingViewTO realExecute() throws ResourceNotFoundException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBViewBuilderReadService service = serviceManager.getDbViewBuilderReadService();

        status = HttpStatus.OK;

        return service.obtainViewByGameId(gameId);
    }
}
