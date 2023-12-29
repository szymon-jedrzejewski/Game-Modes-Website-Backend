package com.gmw.api.rest.activity.comment;

import com.gmw.api.rest.activity.Activity;
import com.gmw.comment.tos.ExistingCommentTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.comment.DBCommentReadService;
import com.gmw.services.exceptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@RequiredArgsConstructor
public class FindAllCommentsActivity extends Activity<List<ExistingCommentTO>> {

    private final Long modId;

    @Override
    protected List<ExistingCommentTO> realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBCommentReadService service = serviceManager.getDbCommentReadService();
            status = HttpStatus.OK;
            return service.obtainCommentsByModId(modId);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
