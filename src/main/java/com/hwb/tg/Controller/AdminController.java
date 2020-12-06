package com.hwb.tg.Controller;

import com.hwb.tg.Shiro.UsernamePasswordTokenModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/11/29 10:59
 */

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 管理员登录
     * @param info
     * @return
     */
    @PostMapping("/login")
    public Map adminLogin(@RequestBody Map info){
        HashMap<String, Object> ret = new HashMap<>();
        String userName = "";
        String password = "";
        try{
            userName = (String) info.get("userName");
            password = (String) info.get("password");
        }catch (Error error){
            ret.put("code",402);
            ret.put("code","参数异常");
        }catch (Exception error){
            ret.put("code",402);
            ret.put("code","参数异常");
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordTokenModel usernamePasswordToken = new UsernamePasswordTokenModel(userName, password, "admin");
        try {
            subject.login(usernamePasswordToken);
            ret.put("code",200);
            ret.put("code","登录成功");
        }catch (UnknownAccountException e){
            ret.put("code",401);
            ret.put("code","用户名不存在");
        }catch (IncorrectCredentialsException e){
            ret.put("code",401);
            ret.put("code","密码错误");
        }catch (Exception e){
            System.out.println(e);
            ret.put("code",401);
            ret.put("code","出现异常");
        }
        return ret;
    }
}
