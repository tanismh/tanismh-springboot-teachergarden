package com.hwb.tg.Service;

import com.hwb.tg.pojo.FinancialReturn;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/7 16:33
 */

public interface FinancialService {
    /**
     * 通过教师ID获取财务信息
     * @param teacherId 教师ID
     * @param pageSize  页面大小
     * @param pageNumber 页码
     * @return
     */
    public List<FinancialReturn> getFinancialByTeacherId(Integer teacherId,
                                                         Integer pageSize,
                                                         Integer pageNumber);

    /**
     * 获取财务信息全部长度
     * @param teacherId 教师ID
     * @return
     */
    public Integer getFinancialLength(Integer teacherId);

    /**
     * 搜索财务信息
     * @param year          搜索的年
     * @param month         搜索的月
     * @param pageNumber    页码
     * @param pageSize      页面大小
     * @param teacherId     教师ID
     * @return
     */
    public List<FinancialReturn> searchFinancial(Integer year,
                                                 Integer month,
                                                 Integer pageNumber,
                                                 Integer pageSize,
                                                 Integer teacherId);
}
