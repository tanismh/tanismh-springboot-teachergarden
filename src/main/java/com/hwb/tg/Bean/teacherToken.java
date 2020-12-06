package com.hwb.tg.Bean;

public class teacherToken {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "teacherToken{" +
                "token='" + token + '\'' +
                '}';
    }
}
