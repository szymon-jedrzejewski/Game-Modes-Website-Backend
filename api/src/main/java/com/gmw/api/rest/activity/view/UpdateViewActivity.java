package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.view.DBViewBuilderService;
import com.gmw.view.tos.ExistingViewTO;
import org.springframework.http.HttpStatus;

public class UpdateViewActivity extends Activity<Void> {

    private final ExistingViewTO view;

    public UpdateViewActivity(ExistingViewTO view) {
        this.view = view;
    }

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBViewBuilderService service = serviceManager.getDbViewBuilderService();
        service.updateView(view);
        status = HttpStatus.OK;
        return null;
    }
}
