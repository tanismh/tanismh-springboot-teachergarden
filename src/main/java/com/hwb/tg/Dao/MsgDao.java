package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Msg;

import java.util.List;


public interface MsgDao {
    public List<Msg> getMsgList(Integer teacherId, Integer begin, Integer pageSize);
}
