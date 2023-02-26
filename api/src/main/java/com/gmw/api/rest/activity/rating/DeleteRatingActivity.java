package com.gmw.api.rest.activity.rating;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.JwtUtils;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.rating.DBRatingService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DeleteRatingActivity extends Activity<Void> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Long id;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotDeletedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBRatingService service = serviceManager.getDbRatingService();
            Long userIdFromRating = service.obtainUserIdByRatingId(id);

            if (userIdFromRating.equals(JwtUtils.extractUserId(token))) {
                service.deleteRating(id);
                status = HttpStatus.OK;
            } else {
                throw new PermissionDeniedException();
            }

        } catch (Exception e) {
            LOGGER.error("Cannot delete rating with id: " + id);
            throw new ResourceNotDeletedException(e);
        }
        return null;
    }
}
