package com.gmw.persistence;

import com.gmw.exceptions.SqlPersistenceManagerException;

import java.util.List;

public interface PersistenceManager {

    Persistable create(Persistable persistable) throws SqlPersistenceManagerException;
    Long update(Persistable persistable);
    void delete(Long id, Class<?> clazz);
    List<Persistable> find(QuerySpec querySpec);
}
