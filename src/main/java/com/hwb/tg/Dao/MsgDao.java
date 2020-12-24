package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MsgDao {
    public List<Msg> getMsgList(Integer teacherId, Integer begin, Integer pageSize);

    /**
     * 添加消息
     *
     * @param msgContent    消息内容
     * @param teacherId     教师Id
     * @param year          年份
     * @param month         月份
     */
    public void updateMsg(String msgContent, Integer teacherId, Integer year, Integer month);
}
