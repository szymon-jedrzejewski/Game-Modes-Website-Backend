package com.gmw.repository.view;


import com.gmw.model.View;
import com.gmw.persistence.PersistenceManager;
import com.gmw.repository.Repository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ViewRepositorySql implements Repository<View> {

    private final PersistenceManager persistenceManager;

    @Override
    public void create(View view) {
        View savedView = (View) persistenceManager.create(view);
    }

    @Override
    public void update(View view) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<View> find(String query) {
        return null;
    }
}
