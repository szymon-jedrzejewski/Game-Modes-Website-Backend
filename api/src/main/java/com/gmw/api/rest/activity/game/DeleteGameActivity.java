package com.gmw.api.rest.activity.game;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.utils.JwtUtils;
import com.gmw.api.rest.utils.PermissionChecker;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.PermissionDeniedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.game.DBGameService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class DeleteGameActivity extends Activity<Void> {

    private final Long id;
    private final String token;

    @Override
    protected Void realExecute() throws ResourceNotDeletedException, UnauthorizedException {
        if (!JwtUtils.isValid(token)) {
            throw new UnauthorizedException();
        }
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            if (PermissionChecker.isAdmin(token))
            {
                DBGameService service = serviceManager.getDbGameService();
                service.deleteGame(id);
                status = HttpStatus.OK;
            } else {
                throw new PermissionDeniedException();
            }
        } catch (Exception e) {
            throw new ResourceNotDeletedException();
        }
        return null;
    }
}
