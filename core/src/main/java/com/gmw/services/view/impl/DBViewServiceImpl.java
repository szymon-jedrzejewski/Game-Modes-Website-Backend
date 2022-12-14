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
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.view.DBViewService;
import com.gmw.view.tos.ExistingFieldTO;
import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewFieldTO;
import com.gmw.view.tos.NewViewTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class DBViewServiceImpl extends DBViewReadServiceImpl implements DBViewService {

    private static final Logger LOGGER = LogManager.getLogger();

    public DBViewServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createView(NewViewTO view) throws ResourceNotDeletedException {

        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();
        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepository();

        try {
            View repositoryView = new View("views");
            repositoryView.setGameId(view.getGameId());

            Long viewId = viewRepositoryManager.create(repositoryView);
            List<Field> fields = mapNewFields(view.getFields());

            for (Field field : fields) {
                field.setViewId(viewId);
                fieldRepositoryManager.create(field);
            }

        } catch (SqlRepositoryException e) {
            LOGGER.error("There was a problem with creating new view!", e);
            throw new ResourceNotDeletedException();
        }
    }

    @Override
    public void deleteView(Long viewId) throws ResourceNotDeletedException {

        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepository();

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
            LOGGER.error("There was a problem with deleting fields from the view with id: " + viewId + "!", e);
            throw new ResourceNotDeletedException();
        }

        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();
        try {
            viewRepositoryManager.delete(viewId);
        } catch (SqlRepositoryException e) {
            LOGGER.error("There was a problem with deleting view with id: " + viewId + "!", e);
            throw new ResourceNotDeletedException();
        }
    }

    @Override
    public void updateView(ExistingViewTO existingView) throws ResourceNotUpdatedException {
        View view = new View("views", existingView.getId(), existingView.getGameId());
        Repository<View> viewRepositoryManager = getRepositoryManager().getViewRepository();
        try {
            viewRepositoryManager.update(view);
        } catch (SqlRepositoryException e) {
            LOGGER.error("There was a problem with updating view with id: " + view.getId() + "!", e);
            throw new ResourceNotUpdatedException();
        }

        List<Field> fields = mapExistingFields(existingView.getFields(), existingView.getId());
        Repository<Field> fieldRepositoryManager = getRepositoryManager().getFieldRepository();
        for (Field field : fields) {
            try {
                fieldRepositoryManager.update(field);
            } catch (SqlRepositoryException e) {
                LOGGER.error("There was a problem with updating field with id: " + field.getId() + "!", e);
                throw new ResourceNotUpdatedException();
            }
        }
    }

    private List<Field> mapExistingFields(List<ExistingFieldTO> existingFields, Long viewId) {
        List<Field> fields = new ArrayList<>();

        existingFields.forEach(existingField -> {
            Field field = new Field("fields");
            field.setId(existingField.getId());
            field.setViewId(viewId);
            field.setType(existingField.getFieldType().toString());
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
            field.setType(newField.getFieldType().toString());
            field.setDescription(newField.getDescription());
            field.setName(newField.getName());
            field.setValues(String.join(",", newField.getValues()));

            fields.add(field);
        });

        return fields;
    }
}
