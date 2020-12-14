package com.hwb.tg.Service;

import com.hwb.tg.Bean.Msg;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/12/7 17:00
 */

public interface MsgService {
    /**
     * 获取消息列表
     * @param jobNumber     工号
     * @param pageSize      页大小
     * @param pageNumebr    页码
     * @return
     */
    public List<Msg> getMsgList(String jobNumber, Integer pageSize, Integer pageNumebr);
}
