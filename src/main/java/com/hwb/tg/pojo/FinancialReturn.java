package com.hwb.tg.pojo;

import java.util.List;

public class FinancialReturn {
    private Integer year;
    private Integer month;
    private Double totalNumber;
    private List<EveryMonthFinancialDetail> everyMonthFinancialDetails;

    @Override
    public String toString() {
        return "FinancialReturn{" +
                "year=" + year +
                ", month=" + month +
                ", totalNumber=" + totalNumber +
                ", everyMonthFinancialDetails=" + everyMonthFinancialDetails +
                '}';
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

    public Double getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Double totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<EveryMonthFinancialDetail> getEveryMonthFinancialDetails() {
        return everyMonthFinancialDetails;
    }

    public void setEveryMonthFinancialDetails(List<EveryMonthFinancialDetail> everyMonthFinancialDetails) {
        this.everyMonthFinancialDetails = everyMonthFinancialDetails;
    }
}
