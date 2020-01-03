package com.decoders.leaves.security;


import com.decoders.leaves.entities.SystemUser;

public class LoginUser {

    private Long id;
    private String username;
    private String password;
    private String privilege;
    private String type;

    public LoginUser() {
    }

    public LoginUser(SystemUser systemUser) {
        this.id = systemUser.getId();
        this.username = systemUser.getUsername();
        this.password = systemUser.getPassword();
        this.privilege = "";
        this.type = "SYS";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
