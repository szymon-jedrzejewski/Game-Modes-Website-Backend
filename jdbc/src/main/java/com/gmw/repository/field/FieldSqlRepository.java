package com.gmw.repository.field;

import com.gmw.model.Field;
import com.gmw.persistence.PersistenceManager;
import com.gmw.repository.Repository;

import java.util.List;

public class FieldSqlRepository implements Repository<Field> {

    private final PersistenceManager persistenceManager;
    public FieldSqlRepository(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public Long create(Field newField) {
        Field field = (Field) persistenceManager.create(newField);
        return field.getId();
    }

    @Override
    public void update(Field field) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Field> find(String query) {
        return null;
    }
}
