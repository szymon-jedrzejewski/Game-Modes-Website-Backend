package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.comment.DBCommentService;
import com.gmw.services.exceptions.*;
import com.gmw.services.fieldvalues.DBFieldValueService;
import com.gmw.services.rating.DBRatingService;

import java.util.List;

public class DeleteModActivity extends Activity<Void> {
    private final Long modId;

    public DeleteModActivity(Long modId) {
        this.modId = modId;
    }

    @Override
    protected Void realExecute() throws ResourceNotDeletedException{
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {

            DBFieldValueService fieldValueService = serviceManager.getDbFieldValueService();
            DBRatingService ratingService = serviceManager.getDbRatingService();
            DBCommentService commentService = serviceManager.getDbCommentService();

            for (Long fieldValueId : fieldValueService.obtainFieldValuesIdsByModId(modId)) {
                fieldValueService.deleteFieldValue(fieldValueId);
            }

            for (Long ratingId : ratingService.obtainRatingsIdsByModId(modId)) {
                ratingService.deleteRating(ratingId);
            }

            List<Long> commentsIds = commentService
                    .obtainCommentsByModId(modId)
                    .stream()
                    .map(ExistingCommentTO::getId)
                    .toList();

            for (Long commentId : commentsIds) {
                commentService.deleteComment(commentId);
            }

            serviceManager.getDbModService().deleteMod(modId);
        } catch (Exception e) {
            throw new ResourceNotDeletedException(e);
        }

        return null;
    }
}
