package com.gmw.viewbuilder.services.viewbuilder.impl;


import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Field;
import com.gmw.model.View;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;
import com.gmw.viewbuilder.tos.ExistingViewTO;
import com.gmw.viewbuilder.tos.NewFieldTO;
import com.gmw.viewbuilder.tos.NewViewTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DBViewBuilderServiceImpl extends DBViewBuilderReadServiceImpl implements DBViewBuilderService {

    private static final Logger logger = LogManager.getLogger();

    public DBViewBuilderServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createView(NewViewTO view) {

        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepositoryManager();
        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepositoryManager();

        try {
            View repositoryView = new View("views", view.getGameId());
            Long viewId = viewRepositoryManager.create(repositoryView);
            List<Field> fields = mapFields(view.getFields());

            for (Field field : fields) {
                field.setViewId(viewId);
                fieldRepositoryManager.create(field);
            }

        } catch (SqlRepositoryException e) {
            logger.error("There was a problem with creating new view!", e);
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
