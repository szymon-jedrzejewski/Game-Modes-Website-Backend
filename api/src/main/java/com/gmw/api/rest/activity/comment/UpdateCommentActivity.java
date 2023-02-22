package com.gmw.api.rest.activity.comment;

import com.gmw.api.rest.activity.Activity;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateCommentActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final ExistingCommentTO category;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            //TODO only creator can edit comment

            serviceManager.getDbCommentService().updateComment(category);
            status = HttpStatus.OK;
        } catch (Exception e) {
            LOGGER.error("Can not update comments with id: " + category.getId());
            throw new ResourceNotUpdatedException(e);
        }
        return null;
    }
}
