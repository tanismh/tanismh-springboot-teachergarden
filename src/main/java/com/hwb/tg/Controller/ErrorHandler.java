package com.hwb.tg.Controller;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandler {
    /**
     * 未授权异常
     *
     * @return
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public Map authorizationException() {
        Map map = new HashMap<>();
        map.put("code", "-1001");
        map.put("msg", "无权限");
        return map;
    }

    /**
     * 未登录异常
     *
     * @return
     */
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public Map authenticationException() {
        Map map = new HashMap<>();
        map.put("code", "-1001");
        map.put("msg", "未登录");
        return map;
    }
}
