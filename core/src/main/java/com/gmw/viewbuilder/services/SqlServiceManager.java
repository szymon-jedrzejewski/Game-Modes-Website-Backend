package com.gmw.viewbuilder.services;

import com.gmw.repository.SqlRepositoryManager;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderReadService;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;
import com.gmw.viewbuilder.services.viewbuilder.impl.DBViewBuilderReadServiceImpl;
import com.gmw.viewbuilder.services.viewbuilder.impl.DBViewBuilderServiceImpl;

public class SqlServiceManager implements ServiceManager {
    @Override
    public DBViewBuilderService getDbViewBuilderService() {
        return new DBViewBuilderServiceImpl(new SqlRepositoryManager());
    }

    @Override
    public DBViewBuilderReadService getDbViewBuilderReadService() {
        return new DBViewBuilderReadServiceImpl(new SqlRepositoryManager());
    }
}
