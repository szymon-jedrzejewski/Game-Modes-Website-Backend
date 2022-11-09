package com.gmw.services.view;

import com.gmw.view.tos.ExistingViewTO;
import com.gmw.view.tos.NewViewTO;

public interface DBViewBuilderService extends DBViewBuilderReadService {
    void createView(NewViewTO view);
    void deleteView(Long viewId);
    void updateView(ExistingViewTO view);
}
