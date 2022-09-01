package com.gmw.viewbuilder.services;

import com.gmw.PersistenceManager;
import com.gmw.sql.SqlConfig;
import com.gmw.sql.SqlPersistenceManager;
import com.gmw.viewbuilder.services.viewbuilder.impl.DBViewBuilderReadServiceImpl;
import com.gmw.viewbuilder.services.viewbuilder.impl.DBViewBuilderServiceImpl;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderReadService;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;

import java.sql.SQLException;

public final class SqlServiceManager implements ServiceManager {

    private final PersistenceManager persistenceManager;

    public SqlServiceManager() {
        this.persistenceManager = new SqlPersistenceManager();
    }

    @Override
    public DBViewBuilderService getDbViewBuilderService() {
        return new DBViewBuilderServiceImpl(persistenceManager);
    }

    @Override
    public DBViewBuilderReadService getDbViewBuilderReadService() {
        return new DBViewBuilderReadServiceImpl(persistenceManager);
    }
}
