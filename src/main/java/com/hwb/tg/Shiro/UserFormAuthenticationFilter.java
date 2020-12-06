package com.hwb.tg.Shiro;

import com.alibaba.fastjson.JSONObject;
//import net.sinorock.aj.common.constant.ErrorCodes;
//import net.sinorock.aj.common.web.def.R;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 何伟斌
 * @date 2020/12/5 21:47
 */



public class UserFormAuthenticationFilter extends FormAuthenticationFilter
{
    public UserFormAuthenticationFilter()
    {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                   Object mappedValue)
    {
        if (((HttpServletRequest)request).getMethod().toUpperCase().equals("OPTIONS"))
        {
            return true;
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
            throws Exception
    {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            //解决 WebUtils.toHttp 往返回response写数据跨域问题
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String origin = httpRequest.getHeader("Origin");
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
            //通过对 Credentials 参数的设置，就可以保持跨域 Ajax 时的 Cookie
            //设置了Allow-Credentials，Allow-Origin就不能为*,需要指明具体的url域
//            httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

            // 返回固定的JSON串
            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
//            WebUtils.toHttp(response).getWriter().print(JSONObject.toJSONString(R.error(ErrorCodes.General.AUTH_EMPTY_ERROR.getCode(),ErrorCodes.General.AUTH_EMPTY_ERROR.getMsg())));
            return false;
        }
    }
}