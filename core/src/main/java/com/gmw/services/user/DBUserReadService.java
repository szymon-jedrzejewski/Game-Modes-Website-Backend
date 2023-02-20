package com.gmw.services.user;

import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.user.tos.ExistingUserTO;

import java.util.List;

public interface DBUserReadService {
    ExistingUserTO obtainUserByEmail(String email) throws ResourceNotFoundException;
    String obtainUserRoleByUserId(Long id) throws ResourceNotFoundException;
    ExistingUserTO obtainUserById(Long userId) throws ResourceNotFoundException;
    List<ExistingUserTO> obtainUsersByIds(List<Long> ids) throws ResourceNotFoundException;
}
