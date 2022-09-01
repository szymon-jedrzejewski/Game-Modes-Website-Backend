package com.gmw.viewbuilder.services;

import com.gmw.viewbuilder.PersistenceManager;
import com.gmw.viewbuilder.services.impl.DBViewBuilderReadServiceImpl;
import com.gmw.viewbuilder.services.impl.DBViewBuilderServiceImpl;

public final class ServiceManager {

    private PersistenceManager persistenceManager;

    public DBViewBuilderService getViewBuilderService() {
        return new DBViewBuilderServiceImpl(persistenceManager);
    }

    public DBViewBuilderReadService getViewBuilderReadService() {
        return new DBViewBuilderReadServiceImpl(persistenceManager);
    }
}
