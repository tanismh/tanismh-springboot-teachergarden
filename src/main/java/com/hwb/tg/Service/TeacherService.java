package com.hwb.tg.Service;


import com.hwb.tg.pojo.TeacherInfo;
import com.hwb.tg.pojo.TeacherLoginInfo;
import com.hwb.tg.pojo.UpdateTeacherInfoParam;

/**
 * @author 何伟斌
 * @date 2020/11/30 14:47
 */

public interface TeacherService {
    /**
     * 获取教师全部信息
     *
     * @param UserName
     * @return
     */
    public TeacherLoginInfo getTeacherInfoByJobNumber(String UserName);

    /**
     * 验证教师权限
     *
     * @param jobNumber  工号
     * @param categoryId 类目Id
     * @return
     */
    public boolean checkTeacherPermission(String jobNumber, Integer categoryId);

    /**
     * 验证教师的密码
     *
     * @param jobNumber 工号
     * @param psw       密码
     * @return
     */
    public boolean checkTeacherPsw(String jobNumber, String psw);

    /**
     * 修改密码
     *
     * @param jobNumber 工号
     * @param psw       密码
     */
    public void updatePsw(String jobNumber,
                          String psw);

    /**
     * 修改个人信息
     *
     * @param updateTeacherInfoParam
     */
    public void updateInfo(UpdateTeacherInfoParam updateTeacherInfoParam);

    /**
     * 根据教师Id获取教师信息
     * @param teacherId     教师Id
     * @return
     */
    public TeacherInfo getTeacherInfoByTeacherId(Integer teacherId);
}
