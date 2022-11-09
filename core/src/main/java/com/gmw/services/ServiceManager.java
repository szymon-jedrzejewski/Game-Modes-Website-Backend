package com.gmw.services;

import com.gmw.services.game.DBGameService;
import com.gmw.services.view.DBViewBuilderReadService;
import com.gmw.services.view.DBViewBuilderService;
import com.gmw.services.game.DBGameReadService;

public interface ServiceManager {

    DBViewBuilderService getDbViewBuilderService();
    DBViewBuilderReadService getDbViewBuilderReadService();

    DBGameService getDbGameService();
    DBGameReadService getDbGameReadService();
}
