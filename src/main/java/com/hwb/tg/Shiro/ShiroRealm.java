package com.hwb.tg.Shiro;

import com.hwb.tg.Service.AdminService;
import com.hwb.tg.Service.TeacherService;
import com.hwb.tg.pojo.AdminLogin;
import com.hwb.tg.pojo.TeacherLoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    AdminService adminServiceImpl;

    @Autowired
    TeacherService teacherServiceImpl;

    //todo 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        String role = "";
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof AdminLogin) {
            role = (String) ((AdminLogin) principal).getRole();
        } else {
            role = (String) ((TeacherLoginInfo) principal).getRole();
        }
        simpleAuthorizationInfo.addRole(role);
        return simpleAuthorizationInfo;
    }


    //todo 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordTokenModel usernamePasswordToken = (UsernamePasswordTokenModel) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        if (usernamePasswordToken.getRole().equals("admin")) {
            AdminLogin loginInfo = adminServiceImpl.getAdminLoginInfoByUserName(userName);
            if (loginInfo == null) {
                throw new UnknownAccountException();
            }else if (loginInfo.getFreeze()==1){
                throw new RuntimeException();
            }
            return new SimpleAuthenticationInfo(loginInfo, loginInfo.getPassword(), "ShiroRealm");
        } else {
            TeacherLoginInfo teacherInfo = teacherServiceImpl.getTeacherInfoByJobNumber(userName);
            if (teacherInfo == null) {
                throw new UnknownAccountException();
            }else if (teacherInfo.getFreeze()==1){
                throw new RuntimeException();
            }
            return new SimpleAuthenticationInfo(teacherInfo, "123456a", "ShiroRealm");
        }
    }

    /**
     * 自定义UsernamePasswordToken后一定得重写 不然会报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof UsernamePasswordTokenModel;
    }
}


