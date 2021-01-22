package com.hwb.tg.Controller;


import com.hwb.tg.Dao.TeacherDao;
import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Service.FinancialService;
import com.hwb.tg.Service.Impl.FinancialServiceImpl;
import com.hwb.tg.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class FinancialController {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    FinancialService financialServiceImpl;

    @RequestMapping(value = "/getFinancial", method = RequestMethod.GET)
    @RequiresRoles(value = {"role:teacher"})
    public Map getFinancial(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                            @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        if (pageNumber == 0)
            pageNumber = 1;
        if (pageSize == 0)
            pageSize = 100;
        HashMap<String, Object> ret = new HashMap<>();
        Integer teacherId = teacherDao.getTeacherIdByJobNumber(((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber());
        List<FinancialReturn> financialByTeacherId = financialServiceImpl.getFinancialByTeacherId(teacherId, pageSize, pageNumber);
        ret.put("code", 200);
        ret.put("msg", "成功");
        Collections.sort(financialByTeacherId, new Comparator<FinancialReturn>() {
            @Override
            public int compare(FinancialReturn o1, FinancialReturn o2) {
                int f1 = -Integer.compare(o1.getYear(), o2.getYear());
                if (f1 == 0) {
                    return -Integer.compare(o1.getMonth(), o2.getMonth());
                } else
                    return f1;
            }
        });
        ret.put("result", financialByTeacherId);
        ret.put("totalLength", financialServiceImpl.getFinancialLength(teacherId));
        return ret;
    }

    @GetMapping("/searchFinancial")
    @RequiresRoles(value = {"role:teacher"})
    public Map searchFinancial(@RequestParam(value = "year", required = false) Integer year,
                               @RequestParam(value = "month", required = false) Integer month,
                               @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code", 200);
        ret.put("msg", "success");
        List<FinancialReturn> financialReturns = financialServiceImpl.searchFinancial(year, month, pageNumber, pageSize, teacherDao.getTeacherIdByJobNumber(((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber()));
        Collections.sort(financialReturns, new Comparator<FinancialReturn>() {
            @Override
            public int compare(FinancialReturn o1, FinancialReturn o2) {
                int f1 = -Integer.compare(o1.getYear(), o2.getYear());
                if (f1 == 0) {
                    return -Integer.compare(o1.getMonth(), o2.getMonth());
                } else
                    return f1;
            }
        });
        ret.put("result", financialReturns);
        return ret;
    }


    @PostMapping("/admin/uploadFinancialFile")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel uploadFinancial(MultipartFile file) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        if (file == null) {
            ret.setCode(CodeEnum.FAILD.getCode());
            ret.setMsg("选择要上传的文件！");
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            ret.setCode(CodeEnum.FAILD.getCode());
            ret.setMsg("文件大小不能超过10M！");
        }
        //获取文件后缀
        System.out.println(System.getProperty("user.dir"));
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        String savePath = System.getProperty("user.dir") + "/file/";
        String fname = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        String strDateFormat = "yyyyMMddHHmmssSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        String filename = simpleDateFormat.format(new Date()) + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.setCode(CodeEnum.FAILD.getCode());
            ret.setMsg("保存文件异常！");
        }
        try {
            ret.setData(financialServiceImpl.uploadFinancialFile(savePath + filename));
        } catch (Exception e) {
            ret.setMsg("发生异常，请检查模板文件");
            ret.setCode(CodeEnum.FAILD.getCode());
        } catch (Error e) {
            ret.setMsg("发生异常，请检查模板文件");
            ret.setCode(CodeEnum.FAILD.getCode());
        }
        return ret;
    }


    /**
     * 上传财务信息接口
     *
     * @param info 含有上传信息的信息
     * @return
     */
    @PostMapping("/admin/uploadFinancial")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel uploadFinancial(@RequestBody Map<String, List<FinancialUpload>> info) {
        ReturnModel ret = null;
        List<FinancialUpload> uploadList = info.get("financials");
        if (financialServiceImpl.uploadFinancial(uploadList)) {
            ret = new ReturnModel(CodeEnum.SUCCESS);
        } else {
            ret = new ReturnModel(CodeEnum.FAILD);
        }
        return ret;
    }

    @PostMapping("/admin/showAllFinancial")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel showFinancials(@RequestBody Map info) {
        Map<String, Integer> lastMonth = financialServiceImpl.getLastMonth();
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        Integer pageSize = (Integer) (info.get("pageSize"));
        Integer pageNumber = (Integer) (info.get("pageNumber"));
        if (pageNumber == null || pageNumber == 0) {
            pageNumber = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 10;
        }
        ret.setData(financialServiceImpl.showAllFinancial(lastMonth.get("year"), lastMonth.get("month"), pageNumber, pageSize));
        return ret;
    }

    @PostMapping("/admin/deleteFinancial")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel deleteFinancial(@RequestBody Map deleteInfo) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        List<Integer> financialIds = (List<Integer>) deleteInfo.get("financialIds");
        financialServiceImpl.deleteFinancial(financialIds);
        return ret;
    }

    @GetMapping("/admin/searchFinancial")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel showTeacherMonth(@RequestParam(value = "jobNumber", required = false) String jobNumber,
                                        @RequestParam(value = "year", required = false) Integer year,
                                        @RequestParam(value = "month", required = false) Integer month,
                                        @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        if (pageNumber == null || pageNumber == 0) {
            pageNumber = 1;
        }
        if (pageSize == null || pageSize == 0) {
            pageSize = 1000;
        }
        Integer teacherId = null;
        if (jobNumber !=null ||!jobNumber.equals("")){
            teacherId = teacherDao.getTeacherIdByJobNumber(jobNumber);
        }else{
            teacherId = null;
        }
        ret.setData(financialServiceImpl.searchTeacherFinancial(teacherId, year, month, pageSize, pageNumber));
        return ret;
    }

    @PostMapping("/admin/editFinancial")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel editFinancial(@RequestBody List<EditFinancial> financials) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        financialServiceImpl.updateFinancial(financials);
        return ret;
    }

}
