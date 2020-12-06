package com.hwb.tg.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/5 22:33
 */

@RestController
public class testCon {
    @RequestMapping("/testLogin")
    public Map testLogin(){
        HashMap<String, Object> ret = new HashMap<>();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("", "123456a");
        subject.login(usernamePasswordToken);
        ret.put("jid",subject.getSession().getId());
        return ret;
    }

    @RequestMapping("/unLogin")
    public Map unLogin(){
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code","222");
        ret.put("msg","未登录");
        return ret;
    }

    @RequiresPermissions("hhh")
    @RequestMapping("test")
    public Map test(){
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code","200");
        ret.put("msg","成功");
        return ret;
    }
}
