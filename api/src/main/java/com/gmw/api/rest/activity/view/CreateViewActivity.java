package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.viewbuilder.services.DBViewBuilderService;
import com.gmw.viewbuilder.tos.NewViewTO;

public class CreateViewActivity extends Activity<Void> {

    private final NewViewTO newView;

    public CreateViewActivity(NewViewTO newView) {
        this.newView = newView;
    }

    @Override
    protected Void realExecute() {
        DBViewBuilderService viewBuilderService = getServiceManager().getViewBuilderService();
        viewBuilderService.createView(newView);

        return null;
    }
}
