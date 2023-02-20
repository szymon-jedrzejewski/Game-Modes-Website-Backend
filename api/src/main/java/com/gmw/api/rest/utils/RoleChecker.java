package com.gmw.api.rest.utils;

import com.gmw.services.ServiceManager;
import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.services.user.DBUserService;
import com.gmw.user.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleChecker {

    public static boolean isAdmin(ServiceManager serviceManager, Long adminId) throws ResourceNotFoundException {
        DBUserService service = serviceManager.getDbUserService();
        return RoleEnum.ADMIN.equals(RoleEnum.valueOf(service.obtainUserRoleByUserId(adminId)));
    }

    public static boolean isUser(ServiceManager serviceManager, Long userId) throws ResourceNotFoundException {
        DBUserService service = serviceManager.getDbUserService();
        return RoleEnum.USER.equals(RoleEnum.valueOf(service.obtainUserRoleByUserId(userId)));
    }
}
