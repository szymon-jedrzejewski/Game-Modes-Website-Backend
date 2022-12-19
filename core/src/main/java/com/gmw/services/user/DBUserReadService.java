package com.gmw.services.user;

import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.user.tos.ExistingUserTO;

public interface DBUserReadService {
    ExistingUserTO obtainUserByEmail(String email) throws ResourceNotFoundException;
    String obtainUserRoleByUserEmail(String email) throws ResourceNotFoundException;
    ExistingUserTO obtainUserById(Long userId) throws ResourceNotFoundException;
}
