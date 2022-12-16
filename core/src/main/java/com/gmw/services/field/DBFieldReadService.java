package com.gmw.services.field;

import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.view.tos.ExistingFieldTO;

import java.util.List;

public interface DBFieldReadService {
    List<ExistingFieldTO> obtainFieldsByViewId(Long viewId) throws ResourceNotFoundException;
}
