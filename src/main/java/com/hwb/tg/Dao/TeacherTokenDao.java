package com.hwb.tg.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherTokenDao {
    public String getToken(String token);
    public void insertToken(String token);
}
