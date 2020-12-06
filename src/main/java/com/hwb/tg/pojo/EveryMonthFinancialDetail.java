package com.hwb.tg.pojo;

public class EveryMonthFinancialDetail {
    private String moneyAbstract;
    private Double money;

    @Override
    public String toString() {
        return "FinancialDetail{" +
                "moneyAbstract='" + moneyAbstract + '\'' +
                ", money=" + money +
                '}';
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
