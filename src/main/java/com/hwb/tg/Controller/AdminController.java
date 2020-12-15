package com.hwb.tg.Controller;

import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Service.AdminService;
import com.hwb.tg.Shiro.UsernamePasswordTokenModel;
import com.hwb.tg.pojo.AdminLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/11/29 10:59
 */

@CrossOrigin("*")
@RestController
public class AdminController {

    @Autowired
    AdminService adminServiceImpl;

    /**
     * 管理员登录
     *
     * @param info
     * @return
     */
    @PostMapping("/admin/login")
    public ReturnModel adminLogin(HttpServletResponse response,
                                  @RequestBody Map info) {
        ReturnModel returnModel;
        String userName = "";
        String password = "";
        try {
            userName = (String) info.get("userName");
            password = (String) info.get("password");
        } catch (Error error) {
            returnModel = new ReturnModel(CodeEnum.API_PARAMETER_ERROR);
        } catch (Exception error) {
            returnModel = new ReturnModel(CodeEnum.API_PARAMETER_ERROR);
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordTokenModel usernamePasswordToken = new UsernamePasswordTokenModel(userName, password, "admin");
        try {
            subject.login(usernamePasswordToken);
            Map retMap = new HashMap<String, String>();
            response.setHeader("Access-Control-Expose-Headers", "token");
            response.addHeader("token", (String) subject.getSession().getId());
            retMap.put("role", ((AdminLogin) subject.getPrincipal()).getRole());
            returnModel = new ReturnModel(CodeEnum.SUCCESS, retMap);
        } catch (UnknownAccountException e) {
            returnModel = new ReturnModel(CodeEnum.Author_ERROR);
        } catch (IncorrectCredentialsException e) {
            returnModel = new ReturnModel(CodeEnum.Author_ERROR);
        } catch (Exception e) {
            returnModel = new ReturnModel(CodeEnum.FAILD);
        }
        return returnModel;
    }

    @PostMapping("/getCmgInfo")
    @RequiresRoles(value = {"role:admin", "role:bigAdmin"}, logical = Logical.OR)
    public ReturnModel getInfo() {
        AdminLogin adminLogin = (AdminLogin) SecurityUtils.getSubject().getPrincipal();
        adminLogin.setPassword(null);
        return new ReturnModel(CodeEnum.SUCCESS, adminLogin);
    }

    /**
     * 管理员修改密码
     *
     * @param resetInfo 包括旧密码、新密码、确认密码
     * @return
     */
    @PostMapping("/resetPsw")
    @RequiresRoles(value = {"role:admin", "role:bigAdmin"}, logical = Logical.OR)
    public ReturnModel resetPsw(@RequestBody Map resetInfo) {
        ReturnModel ret = null;
        String oldPsw = (String) resetInfo.get("oldPsw");
        String newPsw = (String) resetInfo.get("newPsw");
        String confirmPsw = (String) resetInfo.get("confirmPsw");

        Integer adminId = ((AdminLogin) SecurityUtils.getSubject().getPrincipal()).getAdminId();

        if (oldPsw == null || newPsw == null || confirmPsw == null) {
            ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("参数有误");
        } else if (oldPsw.equals("") || newPsw.equals("") || confirmPsw.equals("")) {
            ret = new ReturnModel(CodeEnum.FAILD);
            ret.setMsg("有参数为空");
        } else {
            if (!confirmPsw.equals(newPsw)) {
                ret = new ReturnModel(CodeEnum.FAILD);
                ret.setMsg("两次密码不一致");
            } else if (!adminServiceImpl.checkOldPsw(adminId, oldPsw)) {
                ret = new ReturnModel(CodeEnum.FAILD);
                ret.setMsg("旧密码错误");
            } else {
                adminServiceImpl.resetPsw(adminId, oldPsw, newPsw);
                ret = new ReturnModel(CodeEnum.SUCCESS);
            }
        }
        return ret;
    }
}
