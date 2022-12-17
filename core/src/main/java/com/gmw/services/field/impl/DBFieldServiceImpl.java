package com.gmw.services.field.impl;

import com.gmw.coverters.FieldConverter;
import com.gmw.coverters.TOConverter;
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
        TOConverter<NewFieldTO, Field> converter = new FieldConverter();

        Field field = converter.convertToModel(newField);
        field.setViewId(viewId);

        ServiceUtils.create(repository, field);
    }

    @Override
    public void updateField(ExistingFieldTO existingFieldTO, Long viewId) throws ResourceNotUpdatedException {
        Repository<Field> repository = getRepositoryManager().getFieldRepository();
        TOConverter<NewFieldTO, Field> converter = new FieldConverter();

        Field field = converter.convertToModel(existingFieldTO);
        field.setId(existingFieldTO.getId());
        field.setViewId(viewId);

        ServiceUtils.update(repository, field);
    }

    @Override
    public void deleteField(Long fieldId) throws ResourceNotDeletedException {
        Repository<Field> repository = getRepositoryManager().getFieldRepository();

        ServiceUtils.delete(repository, fieldId);
    }
}
