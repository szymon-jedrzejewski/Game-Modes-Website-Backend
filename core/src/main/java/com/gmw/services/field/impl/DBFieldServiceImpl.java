package com.gmw.services.field.impl;

import com.gmw.model.Field;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.field.DBFieldService;
import com.gmw.view.tos.ExistingFieldTO;
import com.gmw.view.tos.NewFieldTO;

public class DBFieldServiceImpl extends DBFieldReadServiceImpl implements DBFieldService {
    public DBFieldServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createField(NewFieldTO newField, Long viewId) throws ResourceNotCreatedException {
        Repository<Field> repository = getRepositoryManager().getFieldRepository();

        Field field = mapToField(newField);
        field.setViewId(viewId);

        ServiceUtils.create(repository, field);
    }

    @Override
    public void updateField(ExistingFieldTO existingFieldTO, Long viewId) throws ResourceNotUpdatedException {
        Repository<Field> repository = getRepositoryManager().getFieldRepository();

        Field field = mapToField(existingFieldTO);
        field.setViewId(viewId);

        ServiceUtils.update(repository, field);
    }

    @Override
    public void deleteField(Long fieldId) throws ResourceNotDeletedException {
        Repository<Field> repository = getRepositoryManager().getFieldRepository();

        ServiceUtils.delete(repository, fieldId);
    }

    private Field mapToField(NewFieldTO newField) {

        Field field = new Field("fields");
        field.setType(newField.getFieldType().toString());
        field.setDescription(newField.getDescription());
        field.setName(newField.getName());
        field.setLabel(newField.getLabel());

        return field;
    }
}
