package com.gmw.repository;

import com.gmw.entity.View;
import com.gmw.repository.view.ViewRepositorySql;

public class SqlRepositoryManager implements RepositoryManager{

    @Override
    public Repository<View> getViewRepositoryManager() {
        return new ViewRepositorySql();
    }
}
