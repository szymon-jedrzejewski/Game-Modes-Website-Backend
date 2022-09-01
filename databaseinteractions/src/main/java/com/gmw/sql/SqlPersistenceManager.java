package com.gmw.sql;

import com.gmw.PersistenceManager;

import java.util.List;

public class SqlPersistenceManager implements PersistenceManager {

    public SqlPersistenceManager() {
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
