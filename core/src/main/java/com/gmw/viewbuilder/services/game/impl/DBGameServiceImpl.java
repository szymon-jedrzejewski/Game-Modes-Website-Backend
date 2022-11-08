package com.gmw.viewbuilder.services.game.impl;

import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.game.DBGameService;

public class DBGameServiceImpl extends DBGameReadServiceImpl implements DBGameService {
    public DBGameServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
