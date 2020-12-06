package com.hwb.tg.Bean;

import java.util.Date;

public class Teacher {
    private Integer teacherId ;
    private String jobNumber;
    private String password;
    private String name;
    private String professional;
    private Date employmentDate;
    private String longTel;
    private String shortTel;
    private String email;
    private String dingding;
    private String wechat;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
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

    public String getDingding() {
        return dingding;
    }

    public void setDingding(String dingding) {
        this.dingding = dingding;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Teacher(Integer teacherId, String jobNumber, String password, String name, String professional, Date employmentDate, String longTel, String shortTel, String email, String dingding, String wechat) {
        this.teacherId = teacherId;
        this.jobNumber = jobNumber;
        this.password = password;
        this.name = name;
        this.professional = professional;
        this.employmentDate = employmentDate;
        this.longTel = longTel;
        this.shortTel = shortTel;
        this.email = email;
        this.dingding = dingding;
        this.wechat = wechat;
    }

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", jobNumber='" + jobNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", professional='" + professional + '\'' +
                ", employmentDate=" + employmentDate +
                ", longTel='" + longTel + '\'' +
                ", shortTel='" + shortTel + '\'' +
                ", email='" + email + '\'' +
                ", dingding='" + dingding + '\'' +
                ", wechat='" + wechat + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
