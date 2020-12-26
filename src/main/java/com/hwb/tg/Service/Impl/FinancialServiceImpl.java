package com.hwb.tg.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hwb.tg.Dao.FinancialDao;
import com.hwb.tg.Dao.MsgDao;
import com.hwb.tg.Dao.TeacherDao;
import com.hwb.tg.Service.FinancialService;
import com.hwb.tg.Utils.ImportExcel;
import com.hwb.tg.pojo.EveryMonthFinancialDetail;
import com.hwb.tg.pojo.FinancialInfoAdmin;
import com.hwb.tg.pojo.FinancialReturn;
import com.hwb.tg.pojo.FinancialUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/7 16:36
 */
@Service
public class FinancialServiceImpl implements FinancialService {
    @Autowired
    FinancialDao financialDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    MsgDao msgDao;

    @Override
    public List<FinancialReturn> getFinancialByTeacherId(Integer teacherId,
                                                         Integer pageSize,
                                                         Integer pageNumber) {
        List<FinancialReturn> ret = financialDao.getFinancialByTeacherId(teacherId, (pageNumber - 1) * pageSize, pageSize);
        System.out.println(ret);
        for (FinancialReturn financialReturn : ret) {
            Double totalNumber = 0.0;
            for (EveryMonthFinancialDetail e :
                    financialReturn.getEveryMonthFinancialDetails()) {
                totalNumber += e.getMoney();
            }
            financialReturn.setTotalNumber(totalNumber);
        }
        return ret;
    }

    @Override
    public Integer getFinancialLength(Integer teacherId) {
        return financialDao.getFinancialLength(teacherId);
    }

    @Override
    public List<FinancialReturn> searchFinancial(Integer year,
                                                 Integer month,
                                                 Integer pageNumber,
                                                 Integer pageSize,
                                                 Integer teacherId) {
        List<FinancialReturn> ret = null;
        if (pageNumber == 0)
            pageNumber = 1;
        if (pageSize == 0)
            pageSize = 100;
        if ((year != null && !year.equals("")) && (month != null && !month.equals(""))) {
            ret = financialDao.searchFinancialByTeacherIdAndYearAndMonth(teacherId, (pageNumber - 1) * pageSize, pageSize, year, month);
        } else if ((year != null && !year.equals("")) && (month == null || month.equals(""))) {
            ret = financialDao.searchFinancialByTeacherIdAndYear(teacherId, (pageNumber - 1) * pageSize, pageSize, year);
        } else if ((year == null || year.equals("")) && (month != null && !month.equals(""))) {
            ret = financialDao.searchFinancialByTeacherIdAndMonth(teacherId, (pageNumber - 1) * pageSize, pageSize, month);
        } else if ((year == null || year.equals("")) && (month == null || month.equals("")))
            ret = financialDao.getFinancialByTeacherId(teacherId, (pageNumber - 1) * pageNumber, pageNumber);
        for (FinancialReturn financialReturn : ret) {
            Double totalNumber = 0.0;
            for (EveryMonthFinancialDetail e :
                    financialReturn.getEveryMonthFinancialDetails()) {
                totalNumber += e.getMoney();
            }
            financialReturn.setTotalNumber(totalNumber);
        }
        return ret;
    }

    /**
     * 解析财务信息
     *
     * @param path excel文件位置
     * @return
     */
    @Override
    public List<FinancialUpload> uploadFinancialFile(String path) {
        ImportExcel poi = new ImportExcel();
        List<List<String>> list = poi.read(path, 0, 0);
        List<FinancialUpload> financials = new ArrayList<>();
        if (list != null) {

            //获取第一行的标题
            List<String> category = list.get(1);

            // 遍历行
            for (int i = 2; i < list.size(); i++) {
                List<String> cellList = list.get(i);
                FinancialUpload financial = null;

                /* 获取基本参数 */
                int year = Integer.parseInt(cellList.get(0));
                int month = Integer.parseInt(cellList.get(1));
                String jobNumber = cellList.get(2);

                /* 判断是否已经有这个教师 */
                int index = contains(financials, jobNumber, year, month);
                if (index == -1) {
                    financial = new FinancialUpload();
                    financial.setJobNumber(jobNumber);
                    financial.setMonth(month);
                    financial.setYear(year);

                    ArrayList<EveryMonthFinancialDetail> details = new ArrayList<>();
                    for (int j = 3; j < cellList.size(); j++) {

                        /* 如果金额为零的话则跳过 */
                        double money = Double.parseDouble(cellList.get(j));
                        if (money <= 0) {
                            continue;
                        }

                        details.add(new EveryMonthFinancialDetail(category.get(j), money));
                    }
                    financial.setDetailList(details);
                    financial.setT_name(teacherDao.getTeacherNameByJobNumber(jobNumber));
                    financial.setTeacherId(teacherDao.getTeacherIdByJobNumber(jobNumber));
                    financials.add(financial);
                } else {
                    /* 如果已经有这个教师 则继续添加 */
                    financial = financials.get(index);

                    List<EveryMonthFinancialDetail> details = financial.getDetailList();
                    for (int j = 3; j < cellList.size(); j++) {
                        /* 如果金额为零的话则跳过 */
                        double money = Double.parseDouble(cellList.get(j));
                        if (money <= 0) {
                            continue;
                        }

                        details.add(new EveryMonthFinancialDetail(category.get(j), Double.parseDouble(cellList.get(j))));
                    }
                    financial.setDetailList(details);
                }
            }
        }

        return financials;
    }

    /**
     * 上传财务信息接口
     *
     * @param uploadList 财务信息列表
     */
    @Override
    @Transactional
    public boolean uploadFinancial(List<FinancialUpload> uploadList) {
        /* 检查教师ID是否都存在 */
        for (FinancialUpload f :
                uploadList) {
            if (f.getTeacherId() == null)
                return false;
        }
        try {
            for (FinancialUpload f :
                    uploadList) {
                for (EveryMonthFinancialDetail every :
                        f.getDetailList()) {
                    financialDao.uploadFinancial(f.getTeacherId(), f.getYear(), f.getMonth(), every.getMoneyAbstract(), every.getMoney());
                }
                String msgContent = "您" + f.getYear() + "年" + f.getMonth() + "月份的财政信息已更新，点击查看";
                msgDao.updateMsg(msgContent, f.getTeacherId(), f.getYear(), f.getMonth());
            }
            return true;
        } catch (Exception e) {
            return false;
        } catch (Error error) {
            return false;
        }
    }

    /**
     * 获取最后一个日期
     *
     * @return
     */
    @Override
    public Map getLastMonth() {
        return financialDao.getLastMonth();
    }

    /**
     * 查看某个月全部的财务信息
     *
     * @param year  年份
     * @param month 月份
     * @return
     */
    @Override
    public PageInfo<FinancialInfoAdmin> showAllFinancial(Integer year, Integer month, Integer pageNumber, Integer pageSize) {
        List<FinancialInfoAdmin> oneMonth = financialDao.getOneMonth(year, month);
        PageHelper.startPage(pageNumber, pageSize);
        oneMonth.forEach((one) -> {
            one.getFinancialLists().forEach(financialReturn -> {
                Double sum = 0.0;
                for (EveryMonthFinancialDetail every :
                        financialReturn.getEveryMonthFinancialDetails()) {
                    sum += every.getMoney();
                }
                financialReturn.setTotalNumber(sum);
            });
        });
        PageInfo<FinancialInfoAdmin> pageInfo = new PageInfo<>(oneMonth);
        return pageInfo;
    }

    /**
     * 删除财务信息
     */
    @Override
    public void deleteFinancial(List<Integer> financialIds) {
        financialIds.forEach(financialId -> {
            financialDao.deleteFinancial(financialId);
        });
    }

    /**
     * 搜索财务信息
     *
     * @param teacherId
     * @param year
     * @param month
     * @return
     */
    @Override
    public PageInfo<FinancialInfoAdmin> searchTeacherFinancial(Integer teacherId, Integer year, Integer month, Integer pageSize, Integer pageNumber) {
        if ((teacherId != null) && year != null && month != null) {
            PageHelper.startPage(1, 1);
            return new PageInfo<FinancialInfoAdmin>(financialDao.getTeacherYearMonth(year, month, teacherId));
        } else if (teacherId == null && year != null && month != null) {
            PageHelper.startPage(pageNumber, pageSize);
            return new PageInfo<>(financialDao.getOneMonth(year, month));
        } else if (teacherId != null && year == null && month != null) {
            PageHelper.startPage(pageNumber, pageSize);
            return new PageInfo<>(financialDao.getTeacherByIDANdMonth(month, teacherId));
        } else if (teacherId != null && year != null && month == null) {
            PageHelper.startPage(pageNumber, pageSize);
            return new PageInfo<>(financialDao.getTeacherByIDANdYear(year, teacherId));
        } else if (teacherId != null && year == null && month == null) {
            PageHelper.startPage(pageNumber, pageSize);
            return new PageInfo<>(financialDao.getOnlyByTeacherId(teacherId));
        } else if (teacherId == null && year != null && month == null) {
            PageHelper.startPage(pageNumber, pageSize);
            return new PageInfo<>(financialDao.getOnlyByYear(year));
        } else if (teacherId == null && year == null && month != null) {
            PageHelper.startPage(pageNumber, pageSize);
            return new PageInfo<>(financialDao.getOnlyByMonth(month));
        } else {
            PageHelper.startPage(pageNumber, pageSize);
            return new PageInfo<>(new ArrayList<>());
        }
    }

    /**
     * 配合上传财务信息使用 判断列表中有无该教师
     *
     * @param financialUploads 需要识别的列表
     * @param jobNumber        工号
     * @param year             年份
     * @param month            月份
     * @return
     */
    public Integer contains(List<FinancialUpload> financialUploads,
                            String jobNumber,
                            Integer year,
                            Integer month) {
        Integer index = -1;
        for (int i = 0; i < financialUploads.size(); i++) {
            if (financialUploads.get(i).getJobNumber().equals(jobNumber) && financialUploads.get(i).getMonth() == month && financialUploads.get(i).getYear().intValue() == year.intValue()) {
                index = i;
                break;
            }
        }
        return index;
    }
}
