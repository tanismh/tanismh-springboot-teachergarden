package com.hwb.tg.Shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 何伟斌
 * @date 2020/11/30 0:42
 */

public class UsernamePasswordTokenModel implements AuthenticationToken {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;

    public UsernamePasswordTokenModel() {
    }

    public UsernamePasswordTokenModel(String userName, String password) {
        this.username = userName;
        this.password = password;
    }

    public UsernamePasswordTokenModel(String userName, String password, String role) {
        this.username = userName;
        this.password = password;
        this.role = role;
    }

    public UsernamePasswordTokenModel(String userName) {
        this.username = userName;
    }



    @Override
    public String toString() {
        return "UsernamePasswordTokenModel{" +
                "userName='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Object getPrincipal() {
        return this.getUsername();
    }

    @Override
    public Object getCredentials() {
        return this.getPassword();
    }
}
