package com.hwb.tg.pojo;

import java.util.List;
import java.util.Objects;

/**
 * @author 何伟斌
 * @date 2020/12/17 15:28
 */

public class FinancialUpload {
    /**
     * 教师姓名
     */
    private String t_name;
    /**
     * 工号
     */
    private String jobNumber;
    /**
     * 年份
     */
    private Integer year;
    /**
     * 月份
     */
    private Integer month;
    /**
     * 该月份的明细
     */
    private List<EveryMonthFinancialDetail> detailList;

    @Override
    public String toString() {
        return "FinancialUpload{" +
                "t_name='" + t_name + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", detailList=" + detailList +
                '}';
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
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

    public List<EveryMonthFinancialDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<EveryMonthFinancialDetail> detailList) {
        this.detailList = detailList;
    }
}
