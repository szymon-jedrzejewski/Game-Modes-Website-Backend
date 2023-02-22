package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.RoleChecker;
import com.gmw.game.tos.NewGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.game.DBGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateGameActivity extends Activity<Void> {

    private final NewGameTO newGameTO;
    private final Long userId;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (RoleChecker.isAdmin(serviceManager, userId)) {
                DBGameService service = serviceManager.getDbGameService();
                status = HttpStatus.CREATED;
                service.createGame(newGameTO);
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            throw new ResourceNotCreatedException();
        }

        return null;
    }
}
