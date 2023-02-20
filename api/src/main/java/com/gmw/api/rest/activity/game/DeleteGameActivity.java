package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.game.DBGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DeleteGameActivity extends Activity<Void> {

    private final Long id;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotDeletedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId))
            {
                DBGameService service = serviceManager.getDbGameService();
                service.deleteGame(id);
                status = HttpStatus.OK;
            } else {
                setForbidden();
            }
        } catch (Exception e) {
            throw new ResourceNotDeletedException();
        }
        return null;
    }
}
