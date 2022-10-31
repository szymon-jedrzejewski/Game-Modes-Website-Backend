package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.viewbuilder.services.ServiceManager;
import com.gmw.viewbuilder.services.SqlServiceManager;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderReadService;
import com.gmw.viewbuilder.tos.ExistingViewTO;
import org.springframework.http.HttpStatus;

public class FindViewActivity extends Activity<ExistingViewTO> {

    private final Long gameId;

    public FindViewActivity(Long gameId) {
        this.gameId = gameId;
    }

    @Override
    protected ExistingViewTO realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBViewBuilderReadService service = serviceManager.getDbViewBuilderReadService();

        status = HttpStatus.OK;

        return service.obtainViewByGameId(gameId);
    }
}
