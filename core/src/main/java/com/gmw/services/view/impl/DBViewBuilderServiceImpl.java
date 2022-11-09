package com.gmw.services.view.impl;


import com.gmw.exceptions.SqlRepositoryException;
import com.gmw.model.Field;
import com.gmw.model.View;
import com.gmw.persistence.Operator;
import com.gmw.persistence.QueryOperator;
import com.gmw.persistence.QuerySpec;
import com.gmw.persistence.SearchCondition;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.view.DBViewBuilderService;
import com.gmw.view.tos.ExistingFieldTO;
import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewFieldTO;
import com.gmw.view.tos.NewViewTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DBViewBuilderServiceImpl extends DBViewBuilderReadServiceImpl implements DBViewBuilderService {

    private static final Logger LOGGER = LogManager.getLogger();

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
            List<Field> fields = mapNewFields(view.getFields());

            for (Field field : fields) {
                field.setViewId(viewId);
                fieldRepositoryManager.create(field);
            }

        } catch (SqlRepositoryException e) {
            LOGGER.error("There was a problem with creating new view!", e);
        }
    }

    @Override
    public void deleteView(Long viewId) {
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepositoryManager();
        viewRepositoryManager.delete(viewId);

        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepositoryManager();

        QuerySpec querySpec = new QuerySpec();
        querySpec.setClazz(Field.class);
        querySpec.setTableName("fields");
        querySpec.append(QueryOperator.WHERE, new SearchCondition("view_id", Operator.EQUAL_TO, viewId));

        try {
            List<Field> fields = fieldRepositoryManager.find(querySpec);
            for (Field field : fields) {
                fieldRepositoryManager.delete(field.getId());
            }
        } catch (SqlRepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateView(ExistingViewTO existingView) {
        View view = new View("views", existingView.getId(), existingView.getGameId());
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepositoryManager();
        viewRepositoryManager.update(view);

        List<Field> fields = mapExistingFields(existingView.getFields());
        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepositoryManager();
        for (Field field : fields) {
            fieldRepositoryManager.update(field);
        }
    }

    private List<Field> mapExistingFields(List<ExistingFieldTO> existingFields) {
        List<Field> fields = new ArrayList<>();

        existingFields.forEach(existingField -> {
            Field field = new Field("fields");
            field.setType(existingField.getFieldType());
            field.setDescription(existingField.getDescription());
            field.setName(existingField.getName());
            field.setValues(String.join(",", existingField.getValues()));

            fields.add(field);
        });

        return fields;
    }

    private List<Field> mapNewFields(List<NewFieldTO> newFields) {
        List<Field> fields = new ArrayList<>();

        newFields.forEach(newField -> {
            Field field = new Field("fields");
            field.setType(newField.getFieldType());
            field.setDescription(newField.getDescription());
            field.setName(newField.getName());
            field.setValues(String.join(",", newField.getValues()));

            fields.add(field);
        });

        return fields;
    }
}
