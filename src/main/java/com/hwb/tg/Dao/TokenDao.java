package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Token;

public interface TokenDao {
    public Token validationToken(String token);
    public void deletToken(String token);
}
