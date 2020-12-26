package com.hwb.tg.Service;

import com.github.pagehelper.PageInfo;
import com.hwb.tg.pojo.EditFinancial;
import com.hwb.tg.pojo.FinancialInfoAdmin;
import com.hwb.tg.pojo.FinancialReturn;
import com.hwb.tg.pojo.FinancialUpload;

import java.util.List;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/7 16:33
 */

public interface FinancialService {
    /**
     * 通过教师ID获取财务信息
     *
     * @param teacherId  教师ID
     * @param pageSize   页面大小
     * @param pageNumber 页码
     * @return
     */
    public List<FinancialReturn> getFinancialByTeacherId(Integer teacherId,
                                                         Integer pageSize,
                                                         Integer pageNumber);

    /**
     * 获取财务信息全部长度
     *
     * @param teacherId 教师ID
     * @return
     */
    public Integer getFinancialLength(Integer teacherId);

    /**
     * 搜索财务信息
     *
     * @param year       搜索的年
     * @param month      搜索的月
     * @param pageNumber 页码
     * @param pageSize   页面大小
     * @param teacherId  教师ID
     * @return
     */
    public List<FinancialReturn> searchFinancial(Integer year,
                                                 Integer month,
                                                 Integer pageNumber,
                                                 Integer pageSize,
                                                 Integer teacherId);

    /**
     * 解析财务信息模板文件
     *
     * @param path excel文件位置
     * @return
     */
    public List<FinancialUpload> uploadFinancialFile(String path);

    /**
     * 上传财务信息接口
     *
     * @param uploadList 财务信息列表
     */
    public boolean uploadFinancial(List<FinancialUpload> uploadList);

    /**
     * 获取最后一个日期
     *
     * @return
     */
    public Map getLastMonth();

    /**
     * 查看某个月全部的财务信息
     *
     * @param year  年份
     * @param month 月份
     * @return
     */
    public PageInfo<FinancialInfoAdmin> showAllFinancial(Integer year, Integer month, Integer pageNumber, Integer pageSize);

    /**
     * 删除财务信息
     */
    public void deleteFinancial(List<Integer> financialIds);

    /**
     * 搜索财务信息
     *
     * @param teacherId
     * @param year
     * @param month
     * @return
     */
    public PageInfo<FinancialInfoAdmin> searchTeacherFinancial(Integer teacherId, Integer year, Integer month, Integer pageSize, Integer pageNumber);


    /**
     * 更新财务信息
     *
     * @param financials
     */
    public void updateFinancial(List<EditFinancial> financials);
}
