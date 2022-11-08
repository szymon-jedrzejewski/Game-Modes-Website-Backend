package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.viewbuilder.services.ServiceManager;
import com.gmw.viewbuilder.services.SqlServiceManager;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;
import com.gmw.viewbuilder.tos.ExistingViewTO;
import org.springframework.http.HttpStatus;

public class UpdateViewActivity extends Activity<Void> {

    private final ExistingViewTO view;

    public UpdateViewActivity(ExistingViewTO view) {
        this.view = view;
    }

    @Override
    protected Void realExecute() {
        ServiceManager serviceManager = new SqlServiceManager();
        DBViewBuilderService service = serviceManager.getDbViewBuilderService();
        service.updateView(view);
        status = HttpStatus.OK;
        return null;
    }
}
