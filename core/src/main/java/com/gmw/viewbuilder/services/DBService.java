package com.gmw.viewbuilder.services;

import com.gmw.repository.RepositoryManager;

public abstract class DBService {

    private final RepositoryManager repositoryManager;

    public DBService(RepositoryManager repositoryManager) {
        this.repositoryManager = repositoryManager;
    }

    protected RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }
}
