package com.gmw.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
public class User extends Table{
    private Long id;
    private String name;
    private String password;
    private String email;
    private String role;
    private byte[] avatar;


    public User() {
        super(null);
    }

    public User(String tableName) {
        super(tableName);
    }

    public User(String tableName, Long id, String name, String password, String email, String role, byte[] avatar) {
        super(tableName);
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.avatar = avatar;
    }
}
