package com.hwb.tg.Dao;

import com.hwb.tg.pojo.AdminLogin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 何伟斌
 * @date 2020/11/29 16:48
 */
@Mapper
public interface AdminDao {
    /**
     * 登录使用 根据用户名获取用户信息
     * @param userName
     * @return
     */
    public AdminLogin getLoginByUserName(String userName);

    /**
     * 获取管理员部门
     * @param adminId
     * @return
     */
    public String getAdminDepartmentById(Integer adminId);

}
