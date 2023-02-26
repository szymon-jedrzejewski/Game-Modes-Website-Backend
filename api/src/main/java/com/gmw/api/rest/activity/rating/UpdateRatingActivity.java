package com.gmw.api.rest.activity.rating;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.JwtUtils;
import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.rating.DBRatingService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateRatingActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ExistingRatingTO ratingTO;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBRatingService service = serviceManager.getDbRatingService();
            Long userIdFromRating = service.obtainUserIdByRatingId(ratingTO.getId());

            if (userIdFromRating.equals(JwtUtils.extractUserId(token))) {
                service.updateRating(ratingTO);
                status = HttpStatus.OK;
            } else {
                throw new PermissionDeniedException();
            }

        } catch (Exception e) {
            LOGGER.error("Can not update rating with id: " + ratingTO.getId());
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
