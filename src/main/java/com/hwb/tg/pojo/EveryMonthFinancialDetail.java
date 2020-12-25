package com.hwb.tg.pojo;

public class EveryMonthFinancialDetail {
    private String moneyAbstract;
    private Double money;
    private Integer financialId;

    @Override
    public String toString() {
        return "EveryMonthFinancialDetail{" +
                "moneyAbstract='" + moneyAbstract + '\'' +
                ", money=" + money +
                ", financialId=" + financialId +
                '}';
    }

    public Integer getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Integer financialId) {
        this.financialId = financialId;
    }

    public EveryMonthFinancialDetail() {
    }

    public EveryMonthFinancialDetail(String moneyAbstract, Double money) {
        this.moneyAbstract = moneyAbstract;
        this.money = money;
    }

    public String getMoneyAbstract() {
        return moneyAbstract;
    }

    public void setMoneyAbstract(String moneyAbstract) {
        this.moneyAbstract = moneyAbstract;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
