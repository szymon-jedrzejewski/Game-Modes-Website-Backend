package com.gmw.viewbuilder.services;

import com.gmw.repository.SqlRepositoryManager;
import com.gmw.viewbuilder.services.game.DBGameReadService;
import com.gmw.viewbuilder.services.game.DBGameService;
import com.gmw.viewbuilder.services.game.impl.DBGameReadServiceImpl;
import com.gmw.viewbuilder.services.game.impl.DBGameServiceImpl;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderReadService;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;
import com.gmw.viewbuilder.services.viewbuilder.impl.DBViewBuilderReadServiceImpl;
import com.gmw.viewbuilder.services.viewbuilder.impl.DBViewBuilderServiceImpl;

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
