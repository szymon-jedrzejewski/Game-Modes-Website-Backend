package com.gmw.viewbuilder.services.viewbuilder.impl;


import com.gmw.model.Field;
import com.gmw.model.View;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;
import com.gmw.viewbuilder.tos.ExistingViewTO;
import com.gmw.viewbuilder.tos.NewFieldTO;
import com.gmw.viewbuilder.tos.NewViewTO;

import java.util.ArrayList;
import java.util.List;

public class DBViewBuilderServiceImpl extends DBViewBuilderReadServiceImpl implements DBViewBuilderService {


    public DBViewBuilderServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createView(NewViewTO view) {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepositoryManager();
        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepositoryManager();

        View repositoryView = new View("views", view.getGameId());
        Long viewId = viewRepositoryManager.create(repositoryView);

        List<Field> fields = mapFields(view.getFields());

        for (Field field : fields) {
            field.setViewId(viewId);
            fieldRepositoryManager.create(field);
        }
    }

    @Override
    public void deleteView(Long viewId) {

    }

    @Override
    public void updateView(ExistingViewTO view) {

    }

    private List<Field> mapFields(List<NewFieldTO> newFields) {
        List<Field> fields = new ArrayList<>();

        newFields.forEach(newField -> {
            Field field = new Field("fields");
            field.setFieldType(newField.getFieldType());
            field.setDescription(newField.getDescription());
            field.setName(newField.getName());
            field.setValues(String.join(",", newField.getValues()));

            fields.add(field);
        });

        return fields;
    }
}
