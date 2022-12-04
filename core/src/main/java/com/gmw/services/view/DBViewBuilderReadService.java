package com.gmw.services.view;

import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.view.tos.ExistingViewTO;

public interface DBViewBuilderReadService {
    ExistingViewTO obtainViewById(Long viewId) throws ResourceNotFoundException;
    ExistingViewTO obtainViewByGameId(Long gameId) throws ResourceNotFoundException;
}
