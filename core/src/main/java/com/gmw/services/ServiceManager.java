package com.gmw.services;

import com.gmw.services.field.DBFieldReadService;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.game.DBGameReadService;
import com.gmw.services.game.DBGameService;
import com.gmw.services.view.DBViewReadService;
import com.gmw.services.view.DBViewService;

public interface ServiceManager {

    DBViewService getDbViewBuilderService();
    DBViewReadService getDbViewBuilderReadService();
    DBGameService getDbGameService();
    DBGameReadService getDbGameReadService();
    DBFieldReadService getDbFieldReadService();
    DBFieldService getDbFieldService();
}
