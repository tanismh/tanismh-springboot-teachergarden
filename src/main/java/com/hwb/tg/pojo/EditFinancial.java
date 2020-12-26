package com.hwb.tg.pojo;

/**
 * @author 何伟斌
 * @date 2020/12/26 21:22
 */

public class EditFinancial {
    /**
     * ID
     */
    private Integer financialId;
    /**
     * 摘要
     */
    private String abstractFinancial;
    /**
     * 金额
     */
    private Double money;

    @Override
    public String toString() {
        return "EditFinancial{" +
                "financialId=" + financialId +
                ", abstractFinancial='" + abstractFinancial + '\'' +
                ", money=" + money +
                '}';
    }

    public Integer getFinancialId() {
        return financialId;
    }

    public void setFinancialId(Integer financialId) {
        this.financialId = financialId;
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
}
