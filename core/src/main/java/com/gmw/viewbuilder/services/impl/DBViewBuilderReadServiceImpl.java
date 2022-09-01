package com.gmw.viewbuilder.services.impl;

import com.gmw.viewbuilder.PersistenceManager;
import com.gmw.viewbuilder.services.DBService;
import com.gmw.viewbuilder.services.DBViewBuilderReadService;
import com.gmw.viewbuilder.tos.ExistingViewTO;

import java.util.List;

public class DBViewBuilderReadServiceImpl extends DBService implements DBViewBuilderReadService {

    public DBViewBuilderReadServiceImpl(PersistenceManager persistenceManager) {
        super(persistenceManager);
    }

    @Override
    public ExistingViewTO obtainViewById(Long viewId) {
        String query = """
                SELECT * FROM views
                WHERE id = %s
                """;
        return (ExistingViewTO) getPersistenceManager().find(String.format(query));
    }

    @Override
    public ExistingViewTO obtainViewByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ExistingViewTO> obtainViewsByUserId(Long userId) {
        return null;
    }
}
