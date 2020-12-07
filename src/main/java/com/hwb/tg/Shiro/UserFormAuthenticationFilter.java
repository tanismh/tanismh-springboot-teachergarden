package com.hwb.tg.Shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import net.sinorock.aj.common.constant.ErrorCodes;
//import net.sinorock.aj.common.web.def.R;
import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


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

            // 返回固定的JSON串
            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
            ReturnModel returnModel = new ReturnModel(CodeEnum.NOT_LOGIN);
            WebUtils.toHttp(response).getWriter().print(JSON.toJSONString(returnModel));
            return false;
        }
    }
}