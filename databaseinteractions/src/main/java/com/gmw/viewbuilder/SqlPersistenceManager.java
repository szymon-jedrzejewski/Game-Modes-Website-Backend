package com.gmw.viewbuilder;

import java.sql.Connection;
import java.util.List;

public class SqlPersistenceManager implements PersistenceManager{

    private final Connection connection;

    public SqlPersistenceManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object create(String query) {
        return null;
    }

    @Override
    public Long update(String query) {
        return null;
    }

    @Override
    public void delete(String query) {

    }

    @Override
    public List<Object> find(String query) {
        return null;
    }
}
