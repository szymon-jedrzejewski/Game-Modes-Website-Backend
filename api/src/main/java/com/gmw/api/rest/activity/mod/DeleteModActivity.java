package com.gmw.api.rest.activity.mod;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.JwtUtils;
import com.gmw.api.rest.utils.PermissionChecker;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.comment.DBCommentService;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.fieldvalues.DBFieldValueService;
import com.gmw.services.mod.DBModService;
import com.gmw.services.rating.DBRatingService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class DeleteModActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();

    private final Long modId;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotDeletedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBModService modService = serviceManager.getDbModService();
            Long userIdFromMod = modService.findModById(modId).getUserId();

            if (userIdFromMod.equals(JwtUtils.extractUserId(token)) || PermissionChecker.isAdmin(token)) {
                DBFieldValueService fieldValueService = serviceManager.getDbFieldValueService();
                DBRatingService ratingService = serviceManager.getDbRatingService();
                DBCommentService commentService = serviceManager.getDbCommentService();

                try {
                    for (Long fieldValueId : fieldValueService.obtainFieldValuesIdsByModId(modId)) {
                        fieldValueService.deleteFieldValue(fieldValueId);
                    }
                } catch (ResourceNotFoundException e) {
                    LOGGER.warn("No field values to delete!");
                }

                try {
                    for (Long ratingId : ratingService.obtainRatingsIdsByModId(modId)) {
                        ratingService.deleteRating(ratingId);
                    }
                } catch (ResourceNotFoundException e) {
                    LOGGER.warn("No ratings to delete!");
                }

                try {
                    List<Long> commentsIds = commentService
                            .obtainCommentsByModId(modId)
                            .stream()
                            .map(ExistingCommentTO::getId)
                            .toList();

                    for (Long commentId : commentsIds) {
                        commentService.deleteComment(commentId);
                    }
                } catch (ResourceNotFoundException e) {
                    LOGGER.warn("No comments to delete!");
                }

                modService.deleteMod(modId);

                status = HttpStatus.OK;
            }
        } catch (Exception e) {
            throw new ResourceNotDeletedException(e);
        }

        return null;
    }
}
