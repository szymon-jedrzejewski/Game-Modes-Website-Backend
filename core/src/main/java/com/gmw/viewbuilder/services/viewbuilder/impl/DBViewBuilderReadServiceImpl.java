package com.gmw.viewbuilder.services.viewbuilder.impl;

import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.DBService;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderReadService;
import com.gmw.viewbuilder.tos.ExistingViewTO;

import java.util.List;

public class DBViewBuilderReadServiceImpl extends DBService implements DBViewBuilderReadService {

    public DBViewBuilderReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public ExistingViewTO obtainViewById(Long viewId) {
        return null;
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
