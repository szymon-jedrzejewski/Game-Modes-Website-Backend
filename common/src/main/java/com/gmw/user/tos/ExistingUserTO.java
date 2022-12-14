package com.gmw.user.tos;

import com.gmw.user.enums.RoleEnum;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingUserTO extends NewUserTO {
    private Long id;

    @Builder
    public ExistingUserTO(String name, String password, String email, RoleEnum role, byte[] avatar, Long id) {
        super(name, password, email, role, avatar);
        this.id = id;
    }
}
