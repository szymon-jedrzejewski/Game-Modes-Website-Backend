package com.gmw.api.rest.activity.comment;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.security.JwtUtils;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateCommentActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final NewCommentTO comment;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            serviceManager.getDbCommentService().createComment(comment);
            status = HttpStatus.CREATED;

        } catch (Exception e) {
            LOGGER.error("Cannot save new comment!");
            throw new ResourceNotCreatedException(e);
        }
        return null;
    }
}
