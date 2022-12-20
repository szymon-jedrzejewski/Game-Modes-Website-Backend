package com.gmw.services;

import com.gmw.repository.sql.SqlRepositoryManager;
import com.gmw.services.category.DBCategoryReadService;
import com.gmw.services.category.DBCategoryService;
import com.gmw.services.category.impl.DBCategoryReadServiceImpl;
import com.gmw.services.category.impl.DBCategoryServiceImpl;
import com.gmw.services.comment.DBCommentReadService;
import com.gmw.services.comment.DBCommentService;
import com.gmw.services.comment.impl.DBCommentReadServiceImpl;
import com.gmw.services.comment.impl.DBCommentServiceImpl;
import com.gmw.services.field.DBFieldReadService;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.field.impl.DBFieldReadServiceImpl;
import com.gmw.services.field.impl.DBFieldServiceImpl;
import com.gmw.services.game.DBGameReadService;
import com.gmw.services.game.DBGameService;
import com.gmw.services.game.impl.DBGameReadServiceImpl;
import com.gmw.services.game.impl.DBGameServiceImpl;
import com.gmw.services.mod.DBModReadService;
import com.gmw.services.mod.DBModService;
import com.gmw.services.mod.impl.DBModReadServiceImpl;
import com.gmw.services.mod.impl.DBModServiceImpl;
import com.gmw.services.rating.DBRatingReadService;
import com.gmw.services.rating.DBRatingService;
import com.gmw.services.rating.impl.DBRatingReadServiceImpl;
import com.gmw.services.rating.impl.DBRatingServiceImpl;
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
    public DBViewService getDbViewService() {
        return new DBViewServiceImpl(repositoryManager);
    }

    @Override
    public DBViewReadService getDbViewReadService() {
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

    @Override
    public DBRatingReadService getDbRatingReadService() {
        return new DBRatingReadServiceImpl(repositoryManager);
    }

    @Override
    public DBRatingService getDbRatingService() {
        return new DBRatingServiceImpl(repositoryManager);
    }

    @Override
    public DBCategoryService getDbCategoryService() {
        return new DBCategoryServiceImpl(repositoryManager);
    }

    @Override
    public DBCategoryReadService getDbCategoryReadService() {
        return new DBCategoryReadServiceImpl(repositoryManager);
    }

    @Override
    public DBCommentService getDbCommentService() {
        return new DBCommentServiceImpl(repositoryManager);
    }

    @Override
    public DBCommentReadService getDbCommentReadService() {
        return new DBCommentReadServiceImpl(repositoryManager);
    }

    @Override
    public DBModService getDbModService() {
        return new DBModServiceImpl(repositoryManager);
    }

    @Override
    public DBModReadService getDbModReadService() {
        return new DBModReadServiceImpl(repositoryManager);
    }
}
