package com.hwb.tg.pojo;

/**
 * @author 何伟斌
 * @date 2020/12/27 23:36
 */

public class AdminInfo {
    /**
     * 子管理ID
     */
    public Integer adminId;
    /**
     * 子管理用户名
     */
    private String  userName;
    /**
     * 部门
     */
    public String department;
    /**
     * 冻结状态
     */
    public Integer freeze;

    @Override
    public String toString() {
        return "AdminInfo{" +
                "adminId=" + adminId +
                ", userName='" + userName + '\'' +
                ", department='" + department + '\'' +
                ", freeze=" + freeze +
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }
}
