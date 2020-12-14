package com.hwb.tg.Controller;


import com.hwb.tg.Dao.TeacherDao;
import com.hwb.tg.Service.FinancialService;
import com.hwb.tg.pojo.FinancialReturn;
import com.hwb.tg.pojo.TeacherLoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class FinancialController {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    FinancialService financialServiceImpl;

    @RequestMapping(value = "/getFinancial", method = RequestMethod.GET)
    @RequiresRoles(value = {"role:teacher"})
    public Map getFinancial(@RequestParam(value = "pageSize",required = false) Integer pageSize,
                            @RequestParam(value = "pageNumber",required = false) Integer pageNumber){
        if (pageNumber == 0)
            pageNumber = 1;
        if (pageSize == 0)
            pageSize = 100;
        HashMap<String, Object> ret = new HashMap<>();
        Integer teacherId = teacherDao.getTeacherIdByJobNumber(((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber());
        List<FinancialReturn> financialByTeacherId = financialServiceImpl.getFinancialByTeacherId(teacherId, pageSize, pageNumber);
        ret.put("code",200);
        ret.put("msg","成功");
        Collections.sort(financialByTeacherId, new Comparator<FinancialReturn>() {
            @Override
            public int compare(FinancialReturn o1, FinancialReturn o2) {
                int f1 = -Integer.compare(o1.getYear(),o2.getYear());
                if (f1 == 0){
                    return -Integer.compare(o1.getMonth(), o2.getMonth());
                }else
                    return f1;
            }
        });
        ret.put("result",financialByTeacherId);
        ret.put("totalLength",financialServiceImpl.getFinancialLength(teacherId));
        return ret;
    }

    @GetMapping("/searchFinancial")
    @RequiresRoles(value = {"role:teacher"})
    public Map searchFinancial(@RequestParam(value = "year",required = false) Integer year,
                               @RequestParam(value = "month",required = false) Integer month,
                               @RequestParam(value = "pageNumber",required = false) Integer pageNumber,
                               @RequestParam(value = "pageSize",required = false) Integer pageSize){
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code",200);
        ret.put("msg","success");
        List<FinancialReturn> financialReturns = financialServiceImpl.searchFinancial(year, month, pageNumber, pageSize, teacherDao.getTeacherIdByJobNumber(((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber()));
        Collections.sort(financialReturns, new Comparator<FinancialReturn>() {
            @Override
            public int compare(FinancialReturn o1, FinancialReturn o2) {
                int f1 = -Integer.compare(o1.getYear(),o2.getYear());
                if (f1 == 0){
                    return -Integer.compare(o1.getMonth(), o2.getMonth());
                }else
                    return f1;
            }
        });
        ret.put("result",financialReturns);
        return ret;
    }
}
