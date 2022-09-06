package com.gmw.viewbuilder.services.viewbuilder.impl;


import com.gmw.entity.Field;
import com.gmw.entity.View;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;
import com.gmw.viewbuilder.tos.ExistingViewTO;
import com.gmw.viewbuilder.tos.NewViewTO;

import java.util.List;

public class DBViewBuilderServiceImpl extends DBViewBuilderReadServiceImpl implements DBViewBuilderService {


    public DBViewBuilderServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createView(NewViewTO view) {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepositoryManager();
        List<Field> fields = view.getFields().stream().map(field -> new Field()).toList();
        View repositoryView = new View(view.getGameId(), fields);

        viewRepositoryManager.create(repositoryView);
    }

    @Override
    public void deleteView(Long viewId) {

    }

    @Override
    public void updateView(ExistingViewTO view) {

    }
}
