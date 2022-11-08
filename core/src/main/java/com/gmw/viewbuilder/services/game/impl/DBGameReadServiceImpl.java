package com.gmw.viewbuilder.services.game.impl;

import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.game.DBGameReadService;

public class DBGameReadServiceImpl implements DBGameReadService {
    private final RepositoryManager repositoryManager;

    public DBGameReadServiceImpl(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }
}
