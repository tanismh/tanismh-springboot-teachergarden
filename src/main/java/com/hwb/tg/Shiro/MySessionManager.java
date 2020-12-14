package com.hwb.tg.Shiro;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


/**
 * @author 何伟斌
 * @date 2020/12/5 21:37
 */
@Configuration
public class MySessionManager extends DefaultWebSessionManager {

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public MySessionManager() {

        super();
    }


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String id = WebUtils.toHttp(request).getHeader("token");
        //如果请求头中有 Authorization （前端请求头中设置的名字）则其值为sessionId
        if (!StringUtils.isEmpty(id)) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            System.out.println("-------------------------MySession(id)-----------------------------");
            System.out.println(id);
            System.out.println("------------------------------------------------------------------");
            return id;
        } else {
            //否则按默认规则从cookie取sessionId
            System.out.println("-------------------------MySession(super)-----------------------------");
            System.out.println(super.getSessionId(request, response));
            System.out.println("----------------------------------------------------------------------");
            return super.getSessionId(request, response);
        }
    }


}