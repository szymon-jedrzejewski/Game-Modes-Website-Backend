package com.gmw.services.field;

import com.gmw.services.exceptions.ResourceNotFoundException;
import field.tos.ExistingFieldTO;

import java.util.List;

public interface DBFieldReadService {
    List<ExistingFieldTO> obtainFieldsByViewId(Long viewId) throws ResourceNotFoundException;
}
