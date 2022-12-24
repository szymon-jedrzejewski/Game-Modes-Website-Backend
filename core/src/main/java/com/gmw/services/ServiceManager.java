package com.gmw.services;

import com.gmw.services.category.DBCategoryReadService;
import com.gmw.services.category.DBCategoryService;
import com.gmw.services.comment.DBCommentReadService;
import com.gmw.services.comment.DBCommentService;
import com.gmw.services.field.DBFieldReadService;
import com.gmw.services.field.DBFieldService;
import com.gmw.services.fieldvalues.DBFieldValueReadService;
import com.gmw.services.fieldvalues.DBFieldValueService;
import com.gmw.services.game.DBGameReadService;
import com.gmw.services.game.DBGameService;
import com.gmw.services.mod.DBModReadService;
import com.gmw.services.mod.DBModService;
import com.gmw.services.rating.DBRatingReadService;
import com.gmw.services.rating.DBRatingService;
import com.gmw.services.user.DBUserReadService;
import com.gmw.services.user.DBUserService;
import com.gmw.services.view.DBViewReadService;
import com.gmw.services.view.DBViewService;

public interface ServiceManager extends AutoCloseable {

    DBViewService getDbViewService();
    DBViewReadService getDbViewReadService();
    DBGameService getDbGameService();
    DBGameReadService getDbGameReadService();
    DBFieldReadService getDbFieldReadService();
    DBFieldService getDbFieldService();
    DBUserReadService getDbUserReadService();
    DBUserService getDbUserService();
    DBRatingReadService getDbRatingReadService();
    DBRatingService getDbRatingService();
    DBCategoryService getDbCategoryService();
    DBCategoryReadService getDbCategoryReadService();
    DBCommentService getDbCommentService();
    DBCommentReadService getDbCommentReadService();
    DBModService getDbModService();
    DBModReadService getDbModReadService();
    DBFieldValueReadService getDbFieldValueReadService();
    DBFieldValueService getDbFieldValueService();
}
