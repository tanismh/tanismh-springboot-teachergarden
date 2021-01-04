package com.hwb.tg.Dao;

import com.hwb.tg.pojo.AddAdminAccount;
import com.hwb.tg.pojo.AddTeacher;
import com.hwb.tg.pojo.AdminInfo;
import com.hwb.tg.pojo.TeacherInfoAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/23 8:54
 */

@Mapper
@Repository
public interface AccountDao {
    /**
     * 批量添加教师
     *
     * @param teacher
     */
    public void batchAddTeacher(AddTeacher teacher);

    /**
     * 添加子管理员
     *
     * @param adminAccount
     */
    public void addAdmin(AddAdminAccount adminAccount);

    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    public String checkUserName(String userName);

    /**
     * 获取全部教师信息
     *
     * @return
     */
    public List<TeacherInfoAdmin> getTeacher();

    /**
     * 冻结教师账号
     */
    public void freezeTeacher(Integer teacherId);

    /**
     * 解冻教师账号
     */
    public void unFreezeTeacher(Integer teacherId);

    /**
     * 获取单个教师信息
     */
    public TeacherInfoAdmin getATeacherInfo(Integer teacherId);

    /**
     * 更新教师信息
     */
    public void updateTeacher(TeacherInfoAdmin infoAdmin);

    /**
     * 真删除教师
     */
    public void deleteTeacher(Integer teacherId);

    /**
     * 管理员重置教师密码
     *
     * @param teacherId
     * @param psw
     */
    public void resetTeacherPsw(Integer teacherId, String psw);

    /**
     * 获取所有子管理员
     *
     * @return
     */
    public List<AdminInfo> getAllCmgt();

    /**
     * 更新管理员信息
     *
     * @param adminInfo
     */
    public void updateCmgt(AdminInfo adminInfo);

    /**
     * 冻结管理员
     *
     * @param adminId
     */
    public void freezeAdmin(Integer adminId);

    /**
     * 解冻管理员
     *
     * @param adminId
     */
    public void unFreezeAdmin(Integer adminId);

    /**
     * 删除管理员
     *
     * @param admin
     */
    public void deleteAdmin(Integer admin);

    /**
     * 搜索教师（模糊）
     *
     * @param jobNumber
     * @return
     */
    public List<TeacherInfoAdmin> searchTeacher(String jobNumber);
}
