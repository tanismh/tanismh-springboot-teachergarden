package com.hwb.tg.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ExceptionController {
    /**
     * 未登录 访问接口
     * @return
     */
    @RequestMapping("/unLogin")
    public Map unLogin(){
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code",401);
        ret.put("msg","未登录");
        return ret;
    }

    /**
     * 访问未授权页面
     * @return
     */
    @RequestMapping("/unauthorized")
    public Map unauthorized(){
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code",401);
        ret.put("msg","未授权");
        return ret;
    }
}
