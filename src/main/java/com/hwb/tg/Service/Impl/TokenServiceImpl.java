package com.hwb.tg.Service.Impl;

import com.hwb.tg.Service.TokenService;

/**
 * @author 何伟斌
 * @date 2020/12/7 19:19
 */

public class TokenServiceImpl implements TokenService {
    @Override
    public boolean validationToken(String token) {
        return false;
    }

    @Override
    public void logout(String token) {

    }
}
