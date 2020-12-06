package com.hwb.tg.Bean;

public class Token {
    private String token ;
    private String loginTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }
}
