package com.hwb.tg.Dao;


import com.hwb.tg.pojo.FinancialReturn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FinancialDao {
    public List<FinancialReturn> getFinancialByTeacherId(Integer teacherId, Integer begin, Integer pageSize);
    public List<FinancialReturn> searchFinancialByTeacherIdAndYear(Integer teacherId, Integer begin, Integer pageSize, Integer year);
    public List<FinancialReturn> searchFinancialByTeacherIdAndMonth(Integer teacherId, Integer begin, Integer pageSize, Integer month);
    public List<FinancialReturn> searchFinancialByTeacherIdAndYearAndMonth(Integer teacherId, Integer begin, Integer pageSize, Integer year, Integer month);
    public Integer getFinancialLength(Integer teacherId);
}
