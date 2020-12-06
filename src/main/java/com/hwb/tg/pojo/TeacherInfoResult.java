package com.hwb.tg.pojo;

public class TeacherInfoResult {
    private Integer teacherId ;
    private String jobNumber;
    private String teacherName;
    private String professional;
    private String employmentDate; // jobDate
    private String longTel;
    private String shortTel;
    private String email;
    private String dingding;
    private String wechat;
    private String sex;
    private String loginTime;

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
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

    @Override
    public String toString() {
        return "TeacherInfoResult{" +
                "teacherId=" + teacherId +
                ", jobNumber='" + jobNumber + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", professional='" + professional + '\'' +
                ", employmentDate='" + employmentDate + '\'' +
                ", longTel='" + longTel + '\'' +
                ", shortTel='" + shortTel + '\'' +
                ", email='" + email + '\'' +
                ", dingding='" + dingding + '\'' +
                ", wechat='" + wechat + '\'' +
                ", sex='" + sex + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate.split(" ")[0];
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
