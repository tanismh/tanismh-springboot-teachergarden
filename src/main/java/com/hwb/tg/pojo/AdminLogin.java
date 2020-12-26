package com.hwb.tg.pojo;

/**
 * @author 何伟斌
 * @date 2020/11/29 16:59
 */

public class AdminLogin {
    private Integer adminId;
    private String userName;
    private String password;
    private String loginTime;
    private String role;
    private String department;
    private Integer freeze;

    @Override
    public String toString() {
        return "AdminLogin{" +
                "adminId=" + adminId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", role='" + role + '\'' +
                ", department='" + department + '\'' +
                ", freeze=" + freeze +
                '}';
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
