package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.game.DBGameReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public class FindGameByIdActivity extends Activity<ExistingGameTO> {

    private final Long id;

    @Override
    protected ExistingGameTO realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBGameReadService service = serviceManager.getDbGameReadService();
            status = HttpStatus.OK;
            return service.obtainGameById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
