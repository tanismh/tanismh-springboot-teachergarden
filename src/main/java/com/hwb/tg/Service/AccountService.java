package com.hwb.tg.Service;

import com.github.pagehelper.PageInfo;
import com.hwb.tg.pojo.AddAdminAccount;
import com.hwb.tg.pojo.AddTeacher;
import com.hwb.tg.pojo.TeacherInfoAdmin;
import org.apache.commons.math3.analysis.function.Add;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/22 23:51
 */

public interface AccountService {
    /**
     * 解析批量添加账号xls模板
     *
     * @param path
     * @return
     */
    public List<AddTeacher> uploadTeacherAccountFile(String path);

    /**
     * 批量添加教师
     *
     * @param teachers
     */
    public void batchAddTeacher(List<AddTeacher> teachers) throws SQLIntegrityConstraintViolationException;

    /**
     * 添加管理员账号
     *
     * @param adminAccount
     */
    public Boolean addAdmin(AddAdminAccount adminAccount);

    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    public Boolean checkUserName(String userName);

    /**
     * 获取教师信息
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    public PageInfo<TeacherInfoAdmin> getTeacherInfo(Integer pageSize, Integer pageNumber);

    /**
     * 冻结教师
     *
     * @param teacherId
     */
    public void freezeTeacher(Integer teacherId);

    /**
     * 解冻教师
     *
     * @param teacherId
     */
    public void unFreezeTeacher(Integer teacherId);

    /**
     * 获取单个教师全部信息
     *
     * @param teacherId
     * @return
     */
    public TeacherInfoAdmin getATeacherInfo(Integer teacherId);
}
