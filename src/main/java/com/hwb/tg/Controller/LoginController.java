package com.hwb.tg.Controller;

import com.alibaba.fastjson.JSON;
import com.hwb.tg.Shiro.UsernamePasswordTokenModel;
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
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public HashMap login(HttpServletRequest request, HttpServletResponse response, @RequestBody Map info){
//        if (request.getMethod().equals("OPTIONS")) {
//            response.setStatus(HttpServletResponse.SC_OK);
//        }

        // todo  解析用户名、密码、角色
        String userName = ""+info.get("userName");
        String password = ""+info.get("password");

        // todo 加上这一句ajax才能正常获取token
        response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Expose-Headers","Content-Type");

        // todo 返回的map
        HashMap<String, Object> ret = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordTokenModel usernamePasswordToken = new UsernamePasswordTokenModel(userName, password, "teacher");
        try {
            subject.login(usernamePasswordToken);
            ret.put("code",200);
            ret.put("code","登录成功");
            System.out.println("登录成功");
        }catch (UnknownAccountException e){
            ret.put("code",401);
            ret.put("code","用户名不存在");
            System.out.println("用户名不存在");
        }catch (IncorrectCredentialsException e){
            ret.put("code",401);
            ret.put("code","密码错误");
            System.out.println("密码错误");
        }catch (Exception e){
            ret.put("code",401);
            ret.put("code","发生异常");
            System.out.println("发生异常");
        }catch (Error e){
            System.out.println(e);
            ret.put("msg","发生异常");
            System.out.println("发生异常");
        }
        return ret;

    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public String test(@RequestHeader("token") String token){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return JSON.toJSONString("退出成功");
    }

}
