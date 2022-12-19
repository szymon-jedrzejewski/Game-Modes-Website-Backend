package com.gmw.user.tos;

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
    private byte[] avatar;
}
