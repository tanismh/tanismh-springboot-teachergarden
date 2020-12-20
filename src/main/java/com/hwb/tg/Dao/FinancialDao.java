package com.hwb.tg.Dao;


import com.hwb.tg.pojo.FinancialReturn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FinancialDao {
    public List<FinancialReturn> getFinancialByTeacherId(Integer teacherId, Integer begin, Integer pageSize);
    public List<FinancialReturn> searchFinancialByTeacherIdAndYear(Integer teacherId, Integer begin, Integer pageSize, Integer year);
    public List<FinancialReturn> searchFinancialByTeacherIdAndMonth(Integer teacherId, Integer begin, Integer pageSize, Integer month);
    public List<FinancialReturn> searchFinancialByTeacherIdAndYearAndMonth(Integer teacherId, Integer begin, Integer pageSize, Integer year, Integer month);
    public Integer getFinancialLength(Integer teacherId);

    /**
     * 上传财务信息
     *
     * @param teacherId
     * @param year
     * @param month
     * @param abstractFinancial
     * @param money
     */
    public void uploadFinancial(Integer teacherId,Integer year, Integer month,String abstractFinancial,Double money);
}
