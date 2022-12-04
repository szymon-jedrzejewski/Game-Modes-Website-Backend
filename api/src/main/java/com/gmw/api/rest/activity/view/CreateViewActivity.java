package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.SqlServiceManager;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.view.DBViewBuilderService;
import com.gmw.view.tos.NewViewTO;
import org.springframework.http.HttpStatus;

public class CreateViewActivity extends Activity<Void> {

    private final NewViewTO newView;

    public CreateViewActivity(NewViewTO newView) {
        this.newView = newView;
    }

    @Override
    protected Void realExecute() throws ResourceNotDeletedException {
        ServiceManager serviceManager = new SqlServiceManager();
        DBViewBuilderService service = serviceManager.getDbViewBuilderService();
        service.createView(newView);
        status = HttpStatus.CREATED;
        return null;
    }
}
