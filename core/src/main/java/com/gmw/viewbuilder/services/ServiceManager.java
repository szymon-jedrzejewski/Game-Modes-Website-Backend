package com.gmw.viewbuilder.services;

import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderReadService;
import com.gmw.viewbuilder.services.viewbuilder.DBViewBuilderService;

public interface ServiceManager {

    DBViewBuilderService getDbViewBuilderService();
    DBViewBuilderReadService getDbViewBuilderReadService();
}
