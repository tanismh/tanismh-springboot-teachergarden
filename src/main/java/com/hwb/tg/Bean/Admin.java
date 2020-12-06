package com.hwb.tg.Bean;

/**
 * @author 何伟斌
 * @date 2020/11/29 10:42
 */

public class Admin {
    /**
     * 管理员id
     */
    private Integer adminId;
    /**
     * 管理员姓名
     */
    private String userName;
    /**
     * 管理员密码
     */
    private String password;
    /**
     * 管理员部门
     */
    private String department;
    /**
     * 创建时间
     */
    private String creatDate;
    /**
     * 权限Id
     */
    private String permissionId;

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", creatDate='" + creatDate + '\'' +
                ", permissionId='" + permissionId + '\'' +
                '}';
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}
