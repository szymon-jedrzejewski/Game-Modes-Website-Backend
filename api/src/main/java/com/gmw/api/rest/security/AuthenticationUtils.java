package com.gmw.api.rest.security;

import com.gmw.services.ServiceManager;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.user.tos.ExistingUserTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthenticationUtils {

    public boolean isUserAuthenticated(ServiceManager serviceManager, String email) throws ResourceNotFoundException {
        ExistingUserTO user = serviceManager.getDbUserReadService().obtainUserByEmail(email);
        return false;
    }
}
