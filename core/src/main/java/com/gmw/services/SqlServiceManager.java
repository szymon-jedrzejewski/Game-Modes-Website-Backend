package com.gmw.services;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.game.DBGameReadService;
import com.gmw.services.game.DBGameService;
import com.gmw.services.game.impl.DBGameReadServiceImpl;
import com.gmw.services.game.impl.DBGameServiceImpl;
import com.gmw.services.view.DBViewReadService;
import com.gmw.services.view.DBViewService;
import com.gmw.services.view.impl.DBViewReadServiceImpl;
import com.gmw.services.view.impl.DBViewServiceImpl;

public class SqlServiceManager implements ServiceManager {

    private static final SqlRepositoryManager REPOSITORY_MANAGER = new SqlRepositoryManager();
    @Override
    public DBViewService getDbViewBuilderService() {
        return new DBViewServiceImpl(REPOSITORY_MANAGER);
    }

    @Override
    public DBViewReadService getDbViewBuilderReadService() {
        return new DBViewReadServiceImpl(REPOSITORY_MANAGER);
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
