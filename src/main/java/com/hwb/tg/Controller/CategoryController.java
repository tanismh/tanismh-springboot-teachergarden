package com.hwb.tg.Controller;

import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Service.CategoryService;
import com.hwb.tg.pojo.AdminLogin;
import com.hwb.tg.pojo.TeacherLoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryServiceImpl;

    /**
     * 获取其他类目
     *
     * @return
     */
    @RequiresRoles(value = {"role:teacher", "role:admin", "role:bigAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/getElse", method = RequestMethod.POST)
    public Map getElse(HttpServletRequest request, HttpServletResponse response) {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code", 200);
        ret.put("result", categoryServiceImpl.getElse());
        return ret;
    }

    /**
     * 获取教师有权限修改的类目
     *
     * @return
     */
    @RequiresRoles("role:teacher")
    @RequestMapping(value = "/getPermissionCategoryTeacher", method = RequestMethod.POST)
    public Map getPermissionCategoryTeacher() {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code", 200);
        ret.put("msg", "成功");
        Subject subject = SecurityUtils.getSubject();
        TeacherLoginInfo teacherLoginInfo = (TeacherLoginInfo) subject.getPrincipal();
        ret.put("category", categoryServiceImpl.getPermissionCategory(teacherLoginInfo.getJobNumber()));
        return ret;
    }

    /**
     * 获取管理员有权限修改的类目
     *
     * @return
     */
    @RequiresRoles(value = {"role:admin", "role:bigAdmin"}, logical = Logical.OR)
    @RequestMapping(value = "/admin/getPermissionCategory", method = RequestMethod.POST)
    public ReturnModel getPermissionCategoryAdmin() {
        Subject subject = SecurityUtils.getSubject();
        AdminLogin loginInfo = (AdminLogin) subject.getPrincipal();
        ReturnModel returnModel = new ReturnModel(CodeEnum.SUCCESS, categoryServiceImpl.getPermissionCategoryByAdminId(loginInfo.getAdminId()));
        return returnModel;
    }






}
