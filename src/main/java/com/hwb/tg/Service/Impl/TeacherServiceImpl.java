package com.hwb.tg.Service.Impl;

import com.hwb.tg.Bean.PermissionOfCategory;
import com.hwb.tg.Dao.PermissionOfCategoryDao;
import com.hwb.tg.Dao.TeacherDao;
import com.hwb.tg.Service.TeacherService;
import com.hwb.tg.pojo.TeacherInfo;
import com.hwb.tg.pojo.TeacherLoginInfo;
import com.hwb.tg.pojo.UpdateTeacherInfoParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 何伟斌
 * @date 2020/11/30 16:57
 */

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;

    @Autowired
    PermissionOfCategoryDao permissionOfCategoryDao;

    /**
     * 获取教职工信息
     * @param jobNumber 工号
     * @return TeacherLoginInfo 如果没有这个人的话 返回的是null
     */
    @Override
    public TeacherLoginInfo getTeacherInfoByJobNumber(String jobNumber) {
        TeacherLoginInfo ret = teacherDao.teacherLoginByJobNumber(jobNumber);
        if (ret!=null){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            ret.setLoginTime(df.format(new Date()));
        }
        return ret;
    }

    @Override
    public boolean checkTeacherPermission(String jobNumber, Integer categoryId){
        Integer permission = teacherDao.getTeacherPermissionByJobNumber(jobNumber);
        PermissionOfCategory permissionOfCategory = permissionOfCategoryDao.checkPermissionOfTeacher(permission, categoryId);
        if (permissionOfCategory != null)
            return true;
        return false;
    }

    @Override
    public boolean checkTeacherPsw(String jobNumber, String psw){
        if (teacherDao.checkTeacherPsw(jobNumber, psw) != 0){
            return true;
        }else
            return false;
    }

    @Override
    public void updatePsw(String jobNumber,
                          String psw){
        teacherDao.updatePsw(jobNumber, psw);
    }

    @Override
    public void updateInfo(UpdateTeacherInfoParam  updateTeacherInfoParam){
        teacherDao.updateTeacherInfo(updateTeacherInfoParam);
    }

    /**
     * 根据教师Id获取教师信息
     *
     * @param teacherId 教师Id
     * @return
     */
    @Override
    public TeacherInfo getTeacherInfoByTeacherId(Integer teacherId) {
        return teacherDao.getTeacherInfo(teacherId);
    }
}
