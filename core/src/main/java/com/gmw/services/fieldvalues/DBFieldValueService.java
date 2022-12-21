package com.gmw.services.fieldvalues;

import com.gmw.fieldvalue.tos.ExistingFieldValueTO;
import com.gmw.fieldvalue.tos.NewFieldValueTO;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;

public interface DBFieldValueService extends DBFieldValueReadService {
    void createFieldValue(NewFieldValueTO newFieldValueTO) throws ResourceNotCreatedException;
    void updateFieldValue(ExistingFieldValueTO existingFieldValueTO) throws ResourceNotUpdatedException;
    void deleteFieldValue(Long id) throws ResourceNotDeletedException;
}
