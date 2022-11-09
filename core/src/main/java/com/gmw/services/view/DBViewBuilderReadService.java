package com.gmw.services.view;

import com.gmw.view.tos.ExistingViewTO;

public interface DBViewBuilderReadService {
    ExistingViewTO obtainViewById(Long viewId);
    ExistingViewTO obtainViewByGameId(Long gameId);
}
