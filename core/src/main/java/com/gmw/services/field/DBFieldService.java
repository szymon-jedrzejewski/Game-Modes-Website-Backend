package com.gmw.services.field;

import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import field.tos.ExistingFieldTO;
import field.tos.NewFieldTO;

public interface DBFieldService extends DBFieldReadService {

    void createField(NewFieldTO newField, Long viewId) throws ResourceNotCreatedException;
    void updateField(ExistingFieldTO existingFieldTO, Long viewId) throws ResourceNotUpdatedException;
    void deleteField(Long fieldId) throws ResourceNotDeletedException;
}
