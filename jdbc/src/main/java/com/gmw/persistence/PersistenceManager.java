package com.gmw.persistence;

import com.gmw.exceptions.SqlPersistenceManagerException;

import java.util.List;

public interface PersistenceManager extends AutoCloseable{

    Persistable create(Persistable persistable) throws SqlPersistenceManagerException;
    void update(Persistable persistable) throws SqlPersistenceManagerException;
    void delete(Long id, String tableName) throws SqlPersistenceManagerException;
    List<Persistable> find(QuerySpec querySpec) throws SqlPersistenceManagerException;
}
