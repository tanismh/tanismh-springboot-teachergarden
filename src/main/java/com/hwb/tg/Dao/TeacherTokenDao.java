package com.hwb.tg.Dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherTokenDao {
    public String getToken(String token);
    public void insertToken(String token);
}
