package com.gmw.viewbuilder.services;

import com.gmw.viewbuilder.tos.ExistingViewTO;
import com.gmw.viewbuilder.tos.NewViewTO;

public interface DBViewBuilderService extends DBViewBuilderReadService {
    void createView(NewViewTO view);
    void deleteView(Long viewId);
    void updateView(ExistingViewTO view);
}
