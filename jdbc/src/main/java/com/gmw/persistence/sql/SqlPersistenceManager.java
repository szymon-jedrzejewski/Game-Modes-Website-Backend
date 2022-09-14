package com.gmw.persistence.sql;

import com.gmw.persistence.Persistable;
import com.gmw.persistence.PersistenceManager;
import com.gmw.persistence.QuerySpec;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
public class SqlPersistenceManager implements PersistenceManager {
    private final Connection connection;
    @Override
    public Persistable create(Persistable persistable) {
        return null;
    }

    @Override
    public Long update(Persistable persistable) {
        return null;
    }

    @Override
    public void delete(Long id, Class<?> clazz) {

    }

    @Override
    public List<Persistable> find(QuerySpec querySpec) {
        return null;
    }
}
