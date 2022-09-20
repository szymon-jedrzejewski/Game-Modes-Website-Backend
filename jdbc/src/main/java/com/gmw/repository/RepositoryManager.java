package com.gmw.repository;


import com.gmw.model.Field;
import com.gmw.model.View;

public interface RepositoryManager {

    Repository<View> getViewRepositoryManager();
    Repository<Field> getFieldRepositoryManager();
}
