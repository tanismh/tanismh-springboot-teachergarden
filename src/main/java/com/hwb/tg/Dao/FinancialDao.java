package com.hwb.tg.Dao;


import com.hwb.tg.pojo.EditFinancial;
import com.hwb.tg.pojo.FinancialInfoAdmin;
import com.hwb.tg.pojo.FinancialReturn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void uploadFinancial(Integer teacherId, Integer year, Integer month, String abstractFinancial, Double money);

    /**
     * 获取最后一个财务信息的日期
     *
     * @return
     */
    public List<Map<String, Integer>> getLastMonth();

    /**
     * 获取某个月的财务信息
     *
     * @param year  年份
     * @param month 月份
     * @return
     */
    public List<FinancialInfoAdmin> getOneMonth(Integer year, Integer month);

    /**
     * 删除某个月的财务信息
     *
     * @param financialId
     */
    public void deleteFinancial(Integer financialId);

    /**
     * 获取某个教师某个月
     */
    public List<FinancialInfoAdmin> getTeacherYearMonth(Integer year, Integer month, Integer teacherId);

    /**
     * 仅通过月份获取财务
     *
     * @param month
     * @return
     */
    public List<FinancialInfoAdmin> getOnlyByMonth(Integer month);

    /**
     * 仅通过ID获取财务
     *
     * @param teacherId
     * @return
     */
    public List<FinancialInfoAdmin> getOnlyByTeacherId(Integer teacherId);

    /**
     * 仅通过年份获取财务
     *
     * @param year
     * @return
     */
    public List<FinancialInfoAdmin> getOnlyByYear(Integer year);

    /**
     * 通过月份和ID获取
     *
     * @param month
     * @param teacherId
     * @return
     */
    public List<FinancialInfoAdmin> getTeacherByIDANdMonth(Integer month, Integer teacherId);

    /**
     * 通过年份和ID获取
     *
     * @param year
     * @param teacherId
     * @return
     */
    public List<FinancialInfoAdmin> getTeacherByIDANdYear(Integer year, Integer teacherId);

    /**
     * 编辑财务信息
     */
    public void updateFinancial(EditFinancial editFinancial);


    public List<FinancialInfoAdmin> getTeacherYearMonthTest(Integer year, Integer month, Integer teacherId);
}
