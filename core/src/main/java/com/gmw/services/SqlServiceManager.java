package com.gmw.services;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.game.DBGameReadService;
import com.gmw.services.game.DBGameService;
import com.gmw.services.game.impl.DBGameReadServiceImpl;
import com.gmw.services.game.impl.DBGameServiceImpl;
import com.gmw.services.view.DBViewBuilderReadService;
import com.gmw.services.view.DBViewBuilderService;
import com.gmw.services.view.impl.DBViewBuilderReadServiceImpl;
import com.gmw.services.view.impl.DBViewBuilderServiceImpl;

public class SqlServiceManager implements ServiceManager {

    private static final SqlRepositoryManager REPOSITORY_MANAGER = new SqlRepositoryManager();
    @Override
    public DBViewBuilderService getDbViewBuilderService() {
        return new DBViewBuilderServiceImpl(REPOSITORY_MANAGER);
    }

    @Override
    public DBViewBuilderReadService getDbViewBuilderReadService() {
        return new DBViewBuilderReadServiceImpl(REPOSITORY_MANAGER);
    }

    @Override
    public DBGameService getDbGameService() {
        return new DBGameServiceImpl(REPOSITORY_MANAGER);
    }

    @Override
    public DBGameReadService getDbGameReadService() {
        return new DBGameReadServiceImpl(REPOSITORY_MANAGER);
    }
}
