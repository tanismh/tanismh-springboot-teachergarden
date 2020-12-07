package com.hwb.tg.Service;

import com.hwb.tg.Bean.Msg;
import com.hwb.tg.Dao.MsgDao;
import com.hwb.tg.Dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/7 17:01
 */
@Service
public class MsgSeviceImpl implements MsgService {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    MsgDao msgDao;

    @Override
    public List<Msg> getMsgList(String jobNumber,Integer pageSize, Integer pageNumebr){
        Integer teacherId = teacherDao.getTeacherIdByJobNumber(jobNumber);
        List<Msg> msgList = msgDao.getMsgList(teacherId,(pageNumebr - 1)*pageSize,pageSize);
        System.out.println(msgList);
        return msgList;
    }
}
