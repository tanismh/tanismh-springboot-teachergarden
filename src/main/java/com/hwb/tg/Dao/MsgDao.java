package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Msg;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MsgDao {
    public List<Msg> getMsgList(Integer teacherId, Integer begin, Integer pageSize);
}
