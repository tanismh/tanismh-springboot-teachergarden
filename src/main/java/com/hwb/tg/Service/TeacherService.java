package com.hwb.tg.Service;


import com.hwb.tg.pojo.TeacherLoginInfo;

/**
 * @author 何伟斌
 * @date 2020/11/30 14:47
 */

public interface TeacherService {
    public TeacherLoginInfo getTeacherInfoByJobNumber(String UserName);
}
