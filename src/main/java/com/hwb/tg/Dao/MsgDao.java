package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Msg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MsgDao {
    public List<Msg> getMsgList(Integer teacherId, Integer begin, Integer pageSize);
}
