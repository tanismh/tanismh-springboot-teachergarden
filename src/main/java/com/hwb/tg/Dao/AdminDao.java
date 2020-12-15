package com.hwb.tg.Dao;

import com.hwb.tg.pojo.AdminLogin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 何伟斌
 * @date 2020/11/29 16:48
 */
@Mapper
@Repository
public interface AdminDao {
    /**
     * 登录使用 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    public AdminLogin getLoginByUserName(String userName);

    /**
     * 获取管理员部门
     *
     * @param adminId
     * @return
     */
    public String getAdminDepartmentById(Integer adminId);

    /**
     * 通过管理员ID获取密码
     *
     * @param adminId 管理员ID
     * @return
     */
    public String getPswByAdminId(Integer adminId);

    /**
     * 通过管理员ID重置密码
     *
     * @param adminId 管理员ID
     */
    public void resetPsw(Integer adminId,String newPsw);

}
