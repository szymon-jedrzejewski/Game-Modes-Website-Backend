package com.gmw.repository;


import com.gmw.model.View;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.sql.SqlPersistenceManager;
import com.gmw.repository.view.ViewRepositorySql;
import com.gmw.utils.JDBCUtils;

import java.sql.SQLException;

public class SqlRepositoryManager implements RepositoryManager {

    private PersistenceManager persistenceManager;

    public SqlRepositoryManager() {
        try {
            this.persistenceManager = new SqlPersistenceManager(JDBCUtils.getConnection());
        } catch (SQLException e) {

        }
    }

    @Override
    public Repository<View> getViewRepositoryManager() {
        return new ViewRepositorySql(persistenceManager);
    }
}
