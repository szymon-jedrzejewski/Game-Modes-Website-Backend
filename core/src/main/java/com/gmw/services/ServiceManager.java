package com.gmw.services;

import com.gmw.services.game.DBGameService;
import com.gmw.services.view.DBViewReadService;
import com.gmw.services.view.DBViewService;
import com.gmw.services.game.DBGameReadService;

public interface ServiceManager {

    DBViewService getDbViewBuilderService();
    DBViewReadService getDbViewBuilderReadService();

    DBGameService getDbGameService();
    DBGameReadService getDbGameReadService();
}
