package com.gmw.api.rest.activity.comment;

import com.gmw.api.rest.activity.Activity;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
public class FindAllCommentsForModActivity extends Activity<List<ExistingCommentTO>> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Long modId;

    @Override
    protected List<ExistingCommentTO> realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()){
            status = HttpStatus.OK;
            return serviceManager.getDbCommentReadService().obtainCommentsByModId(modId);
        } catch (Exception e) {
            LOGGER.error("Cannot obtain comments!");
            throw new ResourceNotFoundException(e);
        }
    }
}
