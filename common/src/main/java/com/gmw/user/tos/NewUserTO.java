package com.gmw.user.tos;

import com.gmw.user.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserTO {
    private String name;
    private String password;
    private String email;
    private RoleEnum role;
    private byte[] avatar;
}
