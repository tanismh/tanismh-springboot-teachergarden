package com.hwb.tg.pojo;

import java.util.Date;

import javax.validation.constraints.*;

/**
 * @author 何伟斌
 * @date 2020/12/22 23:36
 */

public class AddTeacher {
    /**
     * 教师姓名
     */
    @NotNull
    private String teacherName;
    /**
     * 工号
     */
    @NotNull
    private String jobNumber;
    /**
     * 密码
     */
    @NotNull
    private String password;
    /**
     * 入职日期
     */
    private String EmpTime;
    /**
     * 职位职称
     */
    private String position;
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
        return "AddTeacher{" +
                "teacherName='" + teacherName + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", password='" + password + '\'' +
                ", EmpTime='" + EmpTime + '\'' +
                ", position='" + position + '\'' +
                ", longTel='" + longTel + '\'' +
                ", shortTel='" + shortTel + '\'' +
                ", email='" + email + '\'' +
                ", freeze=" + freeze +
                '}';
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public AddTeacher() {
    }

    public AddTeacher(String teacherName, String jobNumber, String password, String empTime, String position, String longTel, String shortTel, String email) {
        this.teacherName = teacherName;
        this.jobNumber = jobNumber;
        this.password = password;
        EmpTime = empTime;
        this.position = position;
        this.longTel = longTel;
        this.shortTel = shortTel;
        this.email = email;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public String getEmpTime() {
        return EmpTime;
    }

    public void setEmpTime(String empTime) {
        EmpTime = empTime;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
}
