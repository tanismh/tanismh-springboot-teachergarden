package com.hwb.tg.Service;


import com.hwb.tg.pojo.AdminLogin;

/**
 * @author 何伟斌
 * @date 2020/11/29 17:04
 */

public interface AdminService {
    /**
     * 通过用户名获取管理员登录信息
     * @param userName
     * @return
     */
    public AdminLogin getAdminLoginInfoByUserName(String userName);
}
