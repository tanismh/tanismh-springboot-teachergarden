package com.hwb.tg.Shiro;

import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

//@CrossOrigin("*")
@Configuration
public class ShiroConifg {
    //shiroFillter
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);
        /**
         *  anon:无需认证
         *  authc：必须认证之后才能访问
         *  perms：必须有某个资源权限才能访问
         *  role：拥有某个角色权限才能访问l
         */
        LinkedHashMap<String, String> stringStringLinkedHashMap = new LinkedHashMap<>();

        stringStringLinkedHashMap.put("/testLogin","anon");
        stringStringLinkedHashMap.put("/login","anon");
        stringStringLinkedHashMap.put("/file/**","anon");
        stringStringLinkedHashMap.put("/img/**","anon");
        stringStringLinkedHashMap.put("/admin/login","anon");
        stringStringLinkedHashMap.put("/unLogin","anon");
        stringStringLinkedHashMap.put("/**","authc");
        stringStringLinkedHashMap.put("/druid/*","anon");
        bean.setFilterChainDefinitionMap(stringStringLinkedHashMap);
        bean.setLoginUrl("/unLogin");
        bean.setUnauthorizedUrl("/unauthorized");

        Map<String, Filter> filters = new HashMap<>(5);
        filters.put("authc", new UserFormAuthenticationFilter());
        filters.put("roles", new CustomRolesAuthorizationFilter());
        bean.setFilters(filters);
        return bean;
    }
    //shiroDefaultManager

    @Bean
    public SessionManager sessionManager()
    {
        // 将我们继承后重写的shiro session 注册
        MySessionManager sessionManager = new MySessionManager();

        sessionManager.setSessionDAO(new EnterpriseCacheSessionDAO());
        Collection<SessionListener> sessionListeners = new ArrayList<>();
//        sessionListeners.add(customSessionListener());
        sessionManager.setSessionListeners(sessionListeners);
        // 单位为毫秒，600000毫秒为1个小时
        sessionManager.setSessionValidationInterval(3600000 * 12);
        // 3600000 milliseconds = 1 hour
        sessionManager.setGlobalSessionTimeout(3600000 * 12);
        // 是否删除无效的，默认也是开启
        sessionManager.setDeleteInvalidSessions(true);
        // 是否开启 检测，默认开启
        sessionManager.setSessionValidationSchedulerEnabled(true);
        // 创建会话Cookie
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("JSESSIONID");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);

        // 单位为毫秒，600000毫秒为1个小时
        sessionManager.setSessionValidationInterval(3600000 * 12);
        // 3600000 milliseconds = 1 hour
        sessionManager.setGlobalSessionTimeout(3600000 * 12);
        // 是否删除无效的，默认也是开启
        sessionManager.setDeleteInvalidSessions(true);
        return sessionManager;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm,
                                                                  @Qualifier("sessionManager") SessionManager sessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(shiroRealm);
        defaultWebSecurityManager.setSessionManager(sessionManager);
//        defaultSecurityManager.setSessionManager(); = false;
        return defaultWebSecurityManager;
    }
    //realm
    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return authorizationAttributeSourceAdvisor;
    }

//    @Bean
//    public DefaultWebSessionManager defaultWebSessionManager(){
//        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
//        return defaultWebSessionManager;
//    }
}
