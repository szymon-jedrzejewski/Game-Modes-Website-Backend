package com.gmw.api.rest.activity.view;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.tos.NewViewTO;

public class CreateViewActivity extends Activity<Void> {

    private final NewViewTO newView;

    public CreateViewActivity(NewViewTO newView) {
        this.newView = newView;
    }

    @Override
    protected Void realExecute() {

        return null;
    }
}
