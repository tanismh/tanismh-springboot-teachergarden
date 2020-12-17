package com.hwb.tg.Service.Impl;

import com.hwb.tg.Dao.AdminDao;
import com.hwb.tg.Service.AdminService;
import com.hwb.tg.pojo.AdminLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 何伟斌
 * @date 2020/11/29 17:06
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    /**
     * 通过用户名获取管理员登录信息
     *
     * @param userName
     * @return
     */
    @Override
    public AdminLogin getAdminLoginInfoByUserName(String userName) {
        AdminLogin adminLogin = adminDao.getLoginByUserName(userName);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        if (adminLogin != null) {
            adminLogin.setLoginTime(df.format(new Date()));
        }
        return adminLogin;
    }

    /**
     * 管理员修改密码
     *
     * @param adminId 管理员ID
     * @param oldPsw  旧密码
     * @param newPsw  新密码
     */
    @Override
    public void resetPsw(Integer adminId, String oldPsw, String newPsw) {
        adminDao.resetPsw(adminId, newPsw);
    }

    /**
     * 验证旧密码是否正确
     *
     * @param adminId 管理员ID
     * @param oldPsw  旧密码
     * @return
     */
    @Override
    public boolean checkOldPsw(Integer adminId, String oldPsw) {
        if (oldPsw.equals(adminDao.getPswByAdminId(adminId))) {
            return true;
        } else {
            return false;
        }
    }
}
