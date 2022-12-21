package com.gmw.model;

import com.gmw.persistence.Persistable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class User extends Table implements Persistable {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String role;
    private byte[] avatar;


    public User() {
        super("users");
    }
}
