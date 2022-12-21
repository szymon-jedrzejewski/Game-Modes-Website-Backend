package com.gmw.services.fieldvalues.impl;

import com.gmw.repository.RepositoryManager;
import com.gmw.services.DBService;
import com.gmw.services.fieldvalues.DBFieldValueReadService;

public class DBFieldValueReadServiceImpl extends DBService implements DBFieldValueReadService {
    public DBFieldValueReadServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
