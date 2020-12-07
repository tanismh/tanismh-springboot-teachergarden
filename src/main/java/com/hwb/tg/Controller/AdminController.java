package com.hwb.tg.Controller;

import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Shiro.UsernamePasswordTokenModel;
import com.hwb.tg.pojo.AdminLogin;
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
     *
     * @param info
     * @return
     */
    @PostMapping("/login")
    public ReturnModel adminLogin(@RequestBody Map info) {
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
}
