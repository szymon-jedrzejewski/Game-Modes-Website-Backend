package com.gmw.persistence;

import java.util.List;

public interface PersistenceManager {

    Persistable create(Persistable persistable);
    Long update(Persistable persistable);
    void delete(Long id, Class<?> clazz);
    List<Persistable> find(QuerySpec querySpec);
}
