package com.hwb.tg.Controller;

import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Service.AccountService;
import com.hwb.tg.pojo.AddTeacher;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public ReturnModel batchUploadAddTeacher(@RequestBody List<AddTeacher> teachers){
        try{
            accountServiceImpl.batchAddTeacher(teachers);
            ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
            return ret;
        }catch (DuplicateKeyException e){
            ReturnModel ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("添加失败，工号重复，请修改工号");
            return ret;
        }catch (SQLIntegrityConstraintViolationException e){
            ReturnModel ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("添加失败，工号重复，请修改工号");
            return ret;
        }
    }

    
}
