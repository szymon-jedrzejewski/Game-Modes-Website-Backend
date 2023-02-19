package com.gmw.api.rest.activity.user.tos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordTO {
    private Long userId;
    private String password;
}
