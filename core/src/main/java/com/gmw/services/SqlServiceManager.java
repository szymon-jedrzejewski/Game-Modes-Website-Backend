package com.gmw.services;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.field.DBFieldReadService;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.field.impl.DBFieldReadServiceImpl;
import com.gmw.services.field.impl.DBFieldServiceImpl;
import com.gmw.services.game.DBGameReadService;
import com.gmw.services.game.DBGameService;
import com.gmw.services.game.impl.DBGameReadServiceImpl;
import com.gmw.services.game.impl.DBGameServiceImpl;
import com.gmw.services.user.DBUserReadService;
import com.gmw.services.user.DBUserService;
import com.gmw.services.user.impl.DBUserReadServiceImpl;
import com.gmw.services.user.impl.DBUserServiceImpl;
import com.gmw.services.view.DBViewReadService;
import com.gmw.services.view.DBViewService;
import com.gmw.services.view.impl.DBViewReadServiceImpl;
import com.gmw.services.view.impl.DBViewServiceImpl;

public class SqlServiceManager implements ServiceManager {

    private final SqlRepositoryManager repositoryManager;

    public SqlServiceManager(SqlRepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    @Override
    public DBViewService getDbViewBuilderService() {
        return new DBViewServiceImpl(repositoryManager);
    }

    @Override
    public DBViewReadService getDbViewBuilderReadService() {
        return new DBViewReadServiceImpl(repositoryManager);
    }

    @Override
    public DBGameService getDbGameService() {
        return new DBGameServiceImpl(repositoryManager);
    }

    @Override
    public DBGameReadService getDbGameReadService() {
        return new DBGameReadServiceImpl(repositoryManager);
    }

    @Override
    public DBFieldReadService getDbFieldReadService() {
        return new DBFieldReadServiceImpl(repositoryManager);
    }

    @Override
    public DBFieldService getDbFieldService() {
        return new DBFieldServiceImpl(repositoryManager);
    }

    @Override
    public DBUserReadService getDbUserReadService() {
        return new DBUserReadServiceImpl(repositoryManager);
    }

    @Override
    public DBUserService getDbUserService() {
        return new DBUserServiceImpl(repositoryManager);
    }
}
