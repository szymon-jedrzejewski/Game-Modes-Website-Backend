package com.gmw.api.rest.activity.comment;

import com.gmw.api.rest.activity.Activity;
import com.gmw.comment.tos.NewCommentTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateCommentActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final NewCommentTO comment;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
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
