package com.gmw.api.rest.utils;

import com.gmw.services.exceptions.ResourceNotFoundException;
import com.gmw.user.enums.RoleEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PermissionChecker {

    public static boolean isAdmin(String token) throws ResourceNotFoundException {
        String role = JwtUtils.extractUserRole(token);
        return RoleEnum.ADMIN.equals(RoleEnum.valueOf(role));
    }
}
