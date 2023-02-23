package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.security.JwtUtils;
import com.gmw.api.rest.utils.PermissionChecker;
import com.gmw.game.tos.NewGameTO;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.game.DBGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class CreateGameActivity extends Activity<Void> {

    private final NewGameTO newGameTO;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }

        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (PermissionChecker.isAdmin(token)) {
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
