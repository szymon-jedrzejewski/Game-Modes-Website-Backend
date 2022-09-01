package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.viewbuilder.services.ServiceManagerFactory;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;
import com.gmw.viewbuilder.tos.NewViewTO;
import org.springframework.http.HttpStatus;

public class CreateViewActivity extends Activity<Void> {

    private final NewViewTO newView;

    public CreateViewActivity(NewViewTO newView) {
        this.newView = newView;
    }

    @Override
    protected Void realExecute() {
        DBViewBuilderService viewBuilderService = ServiceManagerFactory.getSqlServiceManager().getDbViewBuilderService();
        viewBuilderService.createView(newView);
        status = HttpStatus.OK;
        return null;
    }
}
