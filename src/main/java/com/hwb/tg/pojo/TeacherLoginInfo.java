package com.hwb.tg.pojo;

/**
 * @author 何伟斌
 * @date 2020/11/30 16:51
 */

public class TeacherLoginInfo {
    /**
     * 教师Id
     */
    private Integer teacherId;
    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 密码
     */
    private String password;
    /**
     * 真名
     */
    private String name;
    /**
     * 角色
     */
    private String role;

    /**
     * 冻结状态
     * @return
     */
    private Integer freeze;

    @Override
    public String toString() {
        return "TeacherLoginInfo{" +
                "teacherId=" + teacherId +
                ", loginTime='" + loginTime + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", freeze=" + freeze +
                '}';
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
