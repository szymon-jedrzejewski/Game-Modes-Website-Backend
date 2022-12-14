package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.view.DBViewService;
import org.springframework.http.HttpStatus;

public class DeleteViewActivity extends Activity<Void> {

    private final Long viewId;

    public DeleteViewActivity(Long viewId) {
        this.viewId = viewId;
    }

    @Override
    protected Void realExecute() throws ResourceNotDeletedException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBViewService service = serviceManager.getDbViewBuilderService();
        service.deleteView(viewId);
        status = HttpStatus.OK;
        return null;
    }
}
