package com.gmw.viewbuilder.services.impl;

import com.gmw.viewbuilder.PersistenceManager;
import com.gmw.viewbuilder.services.DBViewBuilderService;
import com.gmw.viewbuilder.tos.ExistingViewTO;
import com.gmw.viewbuilder.tos.NewViewTO;

public class DBViewBuilderServiceImpl extends DBViewBuilderReadServiceImpl implements DBViewBuilderService {

    public DBViewBuilderServiceImpl(PersistenceManager persistenceManager) {
        super(persistenceManager);
    }

    @Override
    public void createView(NewViewTO view) {

    }

    @Override
    public void deleteView(Long viewId) {

    }

    @Override
    public void updateView(ExistingViewTO view) {

    }
}
