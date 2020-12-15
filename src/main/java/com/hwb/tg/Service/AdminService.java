package com.hwb.tg.Service;


import com.hwb.tg.pojo.AdminLogin;

/**
 * @author 何伟斌
 * @date 2020/11/29 17:04
 */

public interface AdminService {
    /**
     * 通过用户名获取管理员登录信息
     *
     * @param userName
     * @return
     */
    public AdminLogin getAdminLoginInfoByUserName(String userName);

    /**
     * 管理员修改密码
     *
     * @param adminId 管理员ID
     * @param oldPsw  旧密码
     * @param newPsw  新密码
     */
    public void resetPsw(Integer adminId, String oldPsw, String newPsw);

    /**
     * 验证旧密码是否正确
     *
     * @param adminId 管理员ID
     * @param oldPsw  旧密码
     * @return
     */
    public boolean checkOldPsw(Integer adminId, String oldPsw);
}
