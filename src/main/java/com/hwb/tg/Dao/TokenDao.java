package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Token;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenDao {
    public Token validationToken(String token);
    public void deletToken(String token);
}
