package com.hwb.tg.Controller;

import com.alibaba.fastjson.JSON;
import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Shiro.UsernamePasswordTokenModel;
import com.hwb.tg.pojo.AdminLogin;
import com.hwb.tg.pojo.TeacherLoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    /**
     * 教职工登录
     *
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ReturnModel login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map info) {

        // todo  解析用户名、密码、角色
        String userName = "" + info.get("userName");
        String password = "" + info.get("password");

        response.setHeader("Access-Control-Expose-Headers","token");

        // todo 返回的map
        HashMap<String, Object> ret = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        ReturnModel returnModel;
        UsernamePasswordTokenModel usernamePasswordToken = new UsernamePasswordTokenModel(userName, password, "teacher");
        try {
            subject.login(usernamePasswordToken);
            ((TeacherLoginInfo) subject.getPrincipal()).setPassword("");
            response.addHeader("token", (String) subject.getSession().getId());
            returnModel = new ReturnModel(CodeEnum.SUCCESS);
        } catch (UnknownAccountException e) {
            returnModel = new ReturnModel(CodeEnum.Author_ERROR);
        } catch (IncorrectCredentialsException e) {
            returnModel = new ReturnModel(CodeEnum.Author_ERROR);
        } catch (RuntimeException e){
            returnModel = new ReturnModel(CodeEnum.FAILD);
            returnModel.setMsg("账号冻结，登录失败");
        } catch (Exception e) {
            System.out.println("_________________________________________________");
            System.out.println(e);
            System.out.println("_________________________________________________");
            returnModel = new ReturnModel(CodeEnum.FAILD);
        }
        return returnModel;

    }

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String test(@RequestHeader("token") String token) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JSON.toJSONString("退出成功");
    }

}
