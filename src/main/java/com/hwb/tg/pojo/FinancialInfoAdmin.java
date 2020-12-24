package com.hwb.tg.pojo;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/21 23:55
 */

public class FinancialInfoAdmin {
    /**
     * 教职工ID
     */
    private Integer teacherId;
    /**
     * 财务信息
     */
    private List<FinancialReturn> financialLists;

    @Override
    public String toString() {
        return "FinancialInfoAdmin{" +
                "teacherId=" + teacherId +
                ", financialLists=" + financialLists +
                '}';
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public List<FinancialReturn> getFinancialLists() {
        return financialLists;
    }

    public void setFinancialLists(List<FinancialReturn> financialLists) {
        this.financialLists = financialLists;
    }
}
