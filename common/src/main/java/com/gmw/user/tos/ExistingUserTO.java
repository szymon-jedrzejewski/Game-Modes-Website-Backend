package com.gmw.user.tos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExistingUserTO extends NewUserTO {
    private Long id;

    @Builder
    public ExistingUserTO(String name, String password, String email, byte[] avatar, Long id) {
        super(name, password, email, avatar);
        this.id = id;
    }
}
