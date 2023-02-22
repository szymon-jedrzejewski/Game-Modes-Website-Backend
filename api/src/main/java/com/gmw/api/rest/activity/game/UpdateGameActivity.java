package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.game.tos.ExistingGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.services.game.DBGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class UpdateGameActivity extends Activity<Void> {

    private final ExistingGameTO game;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotUpdatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                DBGameService service = serviceManager.getDbGameService();
                service.updateGame(game);
                status = HttpStatus.OK;
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            throw new ResourceNotUpdatedException();
        }

        return null;
    }
}
