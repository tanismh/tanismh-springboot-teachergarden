package com.hwb.tg.pojo;

/**
 * @author 何伟斌
 * @date 2020/12/7 21:08
 */

public class TeacherInfo {
    private Integer teacherId;
    private String jobNumber;
    private String teacherName;
    private String name;
    private String professional;
    private String employmentDate; // jobDate
    private String longTel;
    private String shortTel;
    private String email;
    private String loginTime;

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "teacherId=" + teacherId +
                ", jobNumber='" + jobNumber + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", name='" + name + '\'' +
                ", professional='" + professional + '\'' +
                ", employmentDate='" + employmentDate + '\'' +
                ", longTel='" + longTel + '\'' +
                ", shortTel='" + shortTel + '\'' +
                ", email='" + email + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
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

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
