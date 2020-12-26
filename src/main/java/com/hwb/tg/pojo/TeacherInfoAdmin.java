package com.hwb.tg.pojo;

import javax.validation.constraints.NotNull;

/**
 * @author 何伟斌
 * @date 2020/12/26 0:35
 */

public class TeacherInfoAdmin {
    /**
     * 教师ID
     */
    private Integer teacherId;
    /**
     * 教师姓名
     */
    private String name;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 入职日期
     */
    private String employmentDate;
    /**
     * 职位职称
     */
    private String professional;
    /**
     * 手机长号
     */
    private String longTel;
    /**
     * 手机短号
     */
    private String shortTel;
    /**
     * 邮箱
     */
    private String email;

    /**
     * 冻结状态
     */
    private Integer freeze;

    @Override
    public String toString() {
        return "TeacherInfoAdmin{" +
                "teacherId=" + teacherId +
                ", name='" + name + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", employmentDate='" + employmentDate + '\'' +
                ", professional='" + professional + '\'' +
                ", longTel='" + longTel + '\'' +
                ", shortTel='" + shortTel + '\'' +
                ", email='" + email + '\'' +
                ", freeze=" + freeze +
                '}';
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getLongTel() {
        return longTel;
    }

    public void setLongTel(String longTel) {
        this.longTel = longTel;
    }

    public String getShortTel() {
        return shortTel;
    }

    public void setShortTel(String shortTel) {
        this.shortTel = shortTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }
}
