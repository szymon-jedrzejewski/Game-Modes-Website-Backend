package com.gmw.repository.view;


import com.gmw.model.View;
import com.gmw.persistence.PersistenceManager;
import com.gmw.repository.Repository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ViewSqlRepository implements Repository<View> {

    private final PersistenceManager persistenceManager;

    @Override
    public Long create(View newView) {
        View view = (View) persistenceManager.create(newView);
        return view.getId();
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
