package com.hwb.tg.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TokenController {
    @RequestMapping(value = "/checkToken", method = RequestMethod.POST)
    public Map checkToken() {
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("code", 200);
        ret.put("isUse", true);
        return ret;
    }
}
