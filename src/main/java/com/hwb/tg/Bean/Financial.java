package com.hwb.tg.Bean;

public class Financial {
    private Integer financialId;
    private Integer teacherId;
    private Integer year;
    private Integer month;
    private String creatDate;
    private String abstractFinancial;
    private Double money;
    private String issuePerson;

    @Override
    public String toString() {
        return "Financial{" +
                "financialId=" + financialId +
                ", teacherId=" + teacherId +
                ", year=" + year +
                ", month=" + month +
                ", creatDate='" + creatDate + '\'' +
                ", abstractFinancial='" + abstractFinancial + '\'' +
                ", money=" + money +
                ", issuePerson='" + issuePerson + '\'' +
                '}';
    }

    public Integer getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Integer financialId) {
        this.financialId = financialId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getAbstractFinancial() {
        return abstractFinancial;
    }

    public void setAbstractFinancial(String abstractFinancial) {
        this.abstractFinancial = abstractFinancial;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getIssuePerson() {
        return issuePerson;
    }

    public void setIssuePerson(String issuePerson) {
        this.issuePerson = issuePerson;
    }
}
