package com.gmw.services.fieldvalues.impl;

import com.gmw.coverters.FieldValueConverter;
import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.fieldvalue.tos.NewFieldValueTO;
import com.gmw.model.FieldValue;
import com.gmw.repository.Repository;
import com.gmw.repository.RepositoryManager;
import com.gmw.services.ServiceUtils;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.fieldvalues.DBFieldValueService;

public class DBFieldValueServiceImpl extends DBFieldValueReadServiceImpl implements DBFieldValueService {
    public DBFieldValueServiceImpl(RepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void createFieldValue(NewFieldValueTO newFieldValueTO) throws ResourceNotCreatedException {
        Repository<FieldValue> repository = getRepositoryManager().getFieldValueRepository();
        FieldValueConverter converter = new FieldValueConverter();
        FieldValue fieldValue = converter.convertToModel(newFieldValueTO);

        ServiceUtils.create(repository, fieldValue);
    }

    @Override
    public void updateFieldValue(ExistingFieldValueTO existingFieldValueTO) throws ResourceNotUpdatedException {
        Repository<FieldValue> repository = getRepositoryManager().getFieldValueRepository();
        FieldValueConverter converter = new FieldValueConverter();

        FieldValue fieldValue = converter.convertToModel(existingFieldValueTO);
        fieldValue.setId(existingFieldValueTO.getId());

        ServiceUtils.update(repository, fieldValue);
    }

    @Override
    public void deleteFieldValue(Long id) throws ResourceNotDeletedException {
        Repository<FieldValue> repository = getRepositoryManager().getFieldValueRepository();

        ServiceUtils.delete(repository, id);
    }
}
