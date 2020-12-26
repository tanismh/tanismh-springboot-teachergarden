package com.hwb.tg.Controller;

import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Service.AccountService;
import com.hwb.tg.pojo.AddAdminAccount;
import com.hwb.tg.pojo.AddTeacher;
import com.hwb.tg.pojo.TeacherInfoAdmin;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/22 23:49
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountServiceImpl;

    @PostMapping("/admin/uploadTeacherAccountFile")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel uploadTeacherAccountFile(MultipartFile file) {
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
            ret.setData(accountServiceImpl.uploadTeacherAccountFile(savePath + filename));
        } catch (Exception e) {
            ret.setMsg("发生异常，请检查模板文件");
            ret.setCode(CodeEnum.FAILD.getCode());
        } catch (Error e) {
            ret.setMsg("发生异常，请检查模板文件");
            ret.setCode(CodeEnum.FAILD.getCode());
        }
        return ret;
    }


    @PostMapping("/admin/batchUploadAddTeacher")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel batchUploadAddTeacher(@RequestBody List<AddTeacher> teachers) {
        try {
            accountServiceImpl.batchAddTeacher(teachers);
            ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
            return ret;
        } catch (DuplicateKeyException e) {
            ReturnModel ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("添加失败，工号重复，请修改工号");
            return ret;
        } catch (SQLIntegrityConstraintViolationException e) {
            ReturnModel ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("添加失败，工号重复，请修改工号");
            return ret;
        }
    }


    /**
     * 添加管理员账号
     *
     * @param adminAccount
     * @return
     */
    @PostMapping("/admin/addAdmin")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel addAdmin(@RequestBody AddAdminAccount adminAccount) {
        ReturnModel ret = null;
        if (adminAccount.getPassword() == null || adminAccount.getConfirmPassword() == null) {
            ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("密码或确认密码不能为空");
        } else if (!adminAccount.getConfirmPassword().equals(adminAccount.getPassword())) {
            ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("两次密码不一致");
        } else if (adminAccount.getDepartment() == null) {
            ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("部门信息不能为空");
        } else {
            if (accountServiceImpl.addAdmin(adminAccount)) {
                ret = new ReturnModel(CodeEnum.SUCCESS);
            } else {
                ret = new ReturnModel(CodeEnum.FAILD);
                ret.setMsg("添加失败，用户名已存在");
            }

        }
        return ret;
    }

    @GetMapping("/admin/showAllTeacher")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel showAllTeacher(@RequestParam Integer pageSize,
                                      @RequestParam Integer pageNumber) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        ret.setData(accountServiceImpl.getTeacherInfo(pageSize, pageNumber));
        return ret;
    }

    @GetMapping("/admin/freezeTeacher")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel freezeTeacher(@RequestBody Map info) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        accountServiceImpl.freezeTeacher((Integer) info.get("teacherId"));
        return ret;
    }

    @GetMapping("/admin/unFreezeTeacher")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel unFreezeTeacher(@RequestBody Map info) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        accountServiceImpl.unFreezeTeacher((Integer) info.get("teacherId"));
        return ret;
    }

    @PostMapping("/admin/getATeacherInfo")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel getATeacherInfo(@RequestBody Map info ) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        ret.setData(accountServiceImpl.getATeacherInfo((Integer) info.get("teacherId")));
        return ret;
    }

    @PostMapping("/admin/updateTeacher")
    @RequiresRoles(value = {"role:bigAdmin"})
    public ReturnModel updateTeacher(@RequestBody TeacherInfoAdmin teacherInfoAdmin){
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        accountServiceImpl.updateTeacher(teacherInfoAdmin);
        return ret;
    }
}
