package com.gmw.services.view;

import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.view.tos.NewViewTO;

public interface DBViewService extends DBViewReadService {
    Long createView(NewViewTO view) throws ResourceNotCreatedException;
    void deleteView(Long viewId) throws ResourceNotDeletedException;
}
