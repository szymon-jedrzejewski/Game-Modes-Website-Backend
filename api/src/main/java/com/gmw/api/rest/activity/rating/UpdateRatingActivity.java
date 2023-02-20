package com.gmw.api.rest.activity.rating;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.rating.tos.ExistingRatingTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateRatingActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ExistingRatingTO ratingTO;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                serviceManager.getDbRatingService().updateRating(ratingTO);
                status = HttpStatus.OK;
            } else {
                setForbidden();
            }
        } catch (Exception e) {
            LOGGER.error("Can not update rating with id: " + ratingTO.getId());
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
