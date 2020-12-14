package com.hwb.tg.Service.Impl;

import com.hwb.tg.Dao.FinancialDao;
import com.hwb.tg.Service.FinancialService;
import com.hwb.tg.pojo.EveryMonthFinancialDetail;
import com.hwb.tg.pojo.FinancialReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/7 16:36
 */
@Service
public class FinancialServiceImpl implements FinancialService {
    @Autowired
    FinancialDao financialDao;

    @Override
    public List<FinancialReturn> getFinancialByTeacherId(Integer teacherId,
                                                         Integer pageSize,
                                                         Integer pageNumber){
        List<FinancialReturn> ret = financialDao.getFinancialByTeacherId(teacherId,  (pageNumber-1)*pageSize, pageSize);
        System.out.println(ret);
        for (FinancialReturn financialReturn : ret){
            Double totalNumber = 0.0;
            for (EveryMonthFinancialDetail e:
                    financialReturn.getEveryMonthFinancialDetails()) {
                totalNumber += e.getMoney();
            }
            financialReturn.setTotalNumber(totalNumber);
        }
        return ret;
    }

    @Override
    public Integer getFinancialLength(Integer teacherId){
        return financialDao.getFinancialLength(teacherId);
    }

    @Override
    public List<FinancialReturn> searchFinancial(Integer year,
                                                 Integer month,
                                                 Integer pageNumber,
                                                 Integer pageSize,
                                                 Integer teacherId){
        List<FinancialReturn> ret = null;
        if (pageNumber == 0)
            pageNumber = 1;
        if (pageSize == 0)
            pageSize = 100;
        if ((year !=null && !year.equals("")) && (month!=null && !month.equals(""))){
            ret = financialDao.searchFinancialByTeacherIdAndYearAndMonth(teacherId,(pageNumber-1)*pageSize,pageSize,year,month);
        }else if ((year !=null && !year.equals("")) && (month==null || month.equals(""))){
            ret = financialDao.searchFinancialByTeacherIdAndYear(teacherId,(pageNumber-1)*pageSize,pageSize,year);
        }else if ((year ==null || year.equals("")) && (month!=null && !month.equals(""))){
            ret = financialDao.searchFinancialByTeacherIdAndMonth(teacherId,(pageNumber-1)*pageSize,pageSize,month);
        }else if ((year ==null || year.equals("")) && (month==null || month.equals("")))
            ret = financialDao.getFinancialByTeacherId(teacherId,(pageNumber-1)*pageNumber,pageNumber);
        for (FinancialReturn financialReturn : ret){
            Double totalNumber = 0.0;
            for (EveryMonthFinancialDetail e:
                    financialReturn.getEveryMonthFinancialDetails()) {
                totalNumber += e.getMoney();
            }
            financialReturn.setTotalNumber(totalNumber);
        }
        return ret;
    }
}
