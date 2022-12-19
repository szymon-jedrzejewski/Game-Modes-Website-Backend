package com.gmw.repository.sql;


import com.gmw.model.*;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.sql.SqlSafePersistenceManager;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.repository.sql.category.CategoryRepository;
import com.gmw.repository.sql.comment.CommentRepository;
import com.gmw.repository.sql.field.FieldSqlRepository;
import com.gmw.repository.sql.game.GameSqlRepository;
import com.gmw.repository.sql.mod.ModRepository;
import com.gmw.repository.sql.rating.RatingRepository;
import com.gmw.repository.sql.user.UserRepository;
import com.gmw.repository.sql.view.ViewSqlRepository;

import java.sql.Connection;

public class SqlRepositoryManager implements RepositoryManager {
    private final PersistenceManager persistenceManager;

    public SqlRepositoryManager(Connection connection) {
        this.persistenceManager = new SqlSafePersistenceManager(connection);
    }

    @Override
    public Repository<View> getViewRepository() {
        return new ViewSqlRepository(persistenceManager);
    }

    @Override
    public Repository<Field> getFieldRepository() {
        return new FieldSqlRepository(persistenceManager);
    }

    @Override
    public Repository<Game> getGameRepository() {
        return new GameSqlRepository(persistenceManager);
    }

    @Override
    public Repository<Category> getCategoryRepository() {
        return new CategoryRepository(persistenceManager);
    }

    @Override
    public Repository<Comment> getCommentRepository() {
        return new CommentRepository(persistenceManager);
    }

    @Override
    public Repository<Mod> getModRepository() {
        return new ModRepository(persistenceManager);
    }

    @Override
    public Repository<Rating> getRatingRepository() {
        return new RatingRepository(persistenceManager);
    }

    @Override
    public Repository<User> getUserRepository() {
        return new UserRepository(persistenceManager);
    }
}
