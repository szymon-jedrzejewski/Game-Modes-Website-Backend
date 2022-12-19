package com.gmw.services.user;

import com.gmw.services.exceptions.ResourceNotCreatedException;
import com.gmw.services.exceptions.ResourceNotDeletedException;
import com.gmw.services.exceptions.ResourceNotUpdatedException;
import com.gmw.user.tos.ExistingUserTO;
import com.gmw.user.tos.NewUserTO;

public interface DBUserService extends DBUserReadService {
    void createUser(NewUserTO userTO) throws ResourceNotCreatedException;
    void updateUser(ExistingUserTO userTO) throws ResourceNotUpdatedException;
    void deleteUser(Long userId) throws ResourceNotDeletedException;
    void promoteToAdmin(ExistingUserTO userTO) throws ResourceNotUpdatedException;
    void demoteFromAdmin(ExistingUserTO userTO) throws ResourceNotUpdatedException;
}
