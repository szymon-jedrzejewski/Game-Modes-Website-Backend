package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.user.tos.NewUserTO;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateUserActivity extends Activity<Void> {

    private static final Logger LOGGER = LogManager.getLogger();
    private final NewUserTO newUserTO;
    private final PasswordEncoder encoder;

    @Override
    protected Void realExecute() throws ResourceNotCreatedException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            String encodedPassword = encoder.encode(newUserTO.getPassword());
            newUserTO.setPassword(encodedPassword);
            serviceManager.getDbUserService().createUser(newUserTO);
            status = HttpStatus.CREATED;
        } catch (Exception e) {
            LOGGER.error("Cannot save new user!");
            throw new ResourceNotCreatedException(e);
        }
        return null;
    }
}
