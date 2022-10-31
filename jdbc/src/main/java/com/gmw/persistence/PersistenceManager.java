package com.gmw.persistence;

import com.gmw.exceptions.SqlPersistenceManagerException;

import java.util.List;

public interface PersistenceManager {

    Persistable create(Persistable persistable) throws SqlPersistenceManagerException;
    void update(Persistable persistable);
    void delete(Long id, String tableName);
    List<Persistable> find(QuerySpec querySpec) throws SqlPersistenceManagerException;
}
