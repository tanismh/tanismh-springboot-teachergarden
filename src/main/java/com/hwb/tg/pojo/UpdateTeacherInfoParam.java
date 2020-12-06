package com.hwb.tg.pojo;

public class UpdateTeacherInfoParam {
    private String longTel;
    private String shortTel;
    private String email;
    private String dingding;
    private String wechat;
    private String jobNumber;

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Override
    public String toString() {
        return "UpdateTeacherInfoParam{" +
                "longTel='" + longTel + '\'' +
                ", shortTel='" + shortTel + '\'' +
                ", email='" + email + '\'' +
                ", dingding='" + dingding + '\'' +
                ", wechat='" + wechat + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                '}';
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
}
