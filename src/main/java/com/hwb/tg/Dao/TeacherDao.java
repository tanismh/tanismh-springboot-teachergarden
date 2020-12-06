package com.hwb.tg.Dao;

import com.hwb.tg.pojo.TeacherInfoResult;
import com.hwb.tg.pojo.TeacherLoginInfo;
import com.hwb.tg.pojo.UpdateTeacherInfoParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherDao {
    public TeacherLoginInfo teacherLoginByJobNumber(String jobNumber);
    public TeacherInfoResult getTeacherInfo(String teacherName, String teacherId);
    public Integer getTeacherIdByJobNumber(String jobNumber);
    public String getTeacherNameById(Integer teacherId);
    public Integer getTeacherPermissionByJobNumber(String jobNumber);
    public Integer checkTeacherPsw(String jobNumer, String pwd);
    public void updatePsw(String jobNumber, String psw);
    public void updateTeacherInfo(UpdateTeacherInfoParam updateTeacherInfoParam);
}
