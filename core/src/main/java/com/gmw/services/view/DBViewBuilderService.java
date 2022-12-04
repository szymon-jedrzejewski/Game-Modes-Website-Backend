package com.gmw.services.view;

import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewViewTO;

public interface DBViewBuilderService extends DBViewBuilderReadService {
    void createView(NewViewTO view) throws ResourceNotDeletedException;
    void deleteView(Long viewId) throws ResourceNotDeletedException;
    void updateView(ExistingViewTO view) throws ResourceNotUpdatedException;
}
