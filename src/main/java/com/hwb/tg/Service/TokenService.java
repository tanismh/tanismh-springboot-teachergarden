package com.hwb.tg.Service;

import com.hwb.tg.pojo.UpdateTeacherInfoParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/7 19:16
 */

public interface TokenService {
    /**
     * 验证token是否有效
     *
     * @param token
     * @return
     */
    public boolean validationToken(String token);

    /**
     * 退出登录
     *
     * @param token
     */
    public void logout(String token);
}
