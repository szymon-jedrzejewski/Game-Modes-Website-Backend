package com.gmw.viewbuilder.services;

import com.gmw.PersistenceManager;

public abstract class DBService {

    private final PersistenceManager persistenceManager;

    public DBService(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    protected PersistenceManager getPersistenceManager() {
        return this.persistenceManager;
    }
}
