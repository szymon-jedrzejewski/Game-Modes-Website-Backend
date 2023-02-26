package com.gmw.api.rest.activity.user;

import com.gmw.api.rest.activity.Activity;
import com.gmw.api.rest.tos.LoginDTO;
import com.gmw.api.rest.tos.TokenDTO;
import com.gmw.api.rest.security.JwtUtils;
import com.gmw.services.ServiceManager;
import com.gmw.services.ServiceManagerFactoryImpl;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.exceptions.UnauthorizedException;
import com.gmw.services.user.DBUserReadService;
import com.gmw.user.tos.ExistingUserTO;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class LoginActivity extends Activity<TokenDTO> {

    private static final Logger LOGGER = LogManager.getLogger();
    private LoginDTO loginDTO;
    private PasswordEncoder encoder;

    @Override
    protected TokenDTO realExecute() throws ResourceNotFoundException {
        try (ServiceManager serviceManager = new ServiceManagerFactoryImpl().createSqlServiceManager()) {
            DBUserReadService service = serviceManager.getDbUserReadService();
            ExistingUserTO user = service.obtainUserByEmail(loginDTO.getEmail());
            String password = encoder.encode(loginDTO.getPassword());

            if (user.getPassword().equals(password)) {
                String role = service.obtainUserRoleByUserId(user.getId());
                JwtUtils jwtUtils = new JwtUtils();
                String token = jwtUtils.generateJwtToken(user, role);
                status = HttpStatus.OK;
                return new TokenDTO(token);
            } else {
                throw new UnauthorizedException();
            }
        } catch (Exception e) {
            LOGGER.error("Cannot save new user!");
            throw new ResourceNotFoundException(e);
        }
    }
}
