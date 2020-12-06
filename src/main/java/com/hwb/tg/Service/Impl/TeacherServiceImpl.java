package com.hwb.tg.Service.Impl;

import com.hwb.tg.Dao.TeacherDao;
import com.hwb.tg.Service.TeacherService;
import com.hwb.tg.pojo.TeacherLoginInfo;
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
}
