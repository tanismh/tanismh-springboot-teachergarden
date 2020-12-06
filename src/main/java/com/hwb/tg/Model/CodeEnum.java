package com.hwb.tg.Model;

/**
 * @author 何伟斌
 * @date 2020/12/6 12:01
 */

public enum CodeEnum {
    SUCCESS(200, "处理成功"),
    FAILD(400, "处理失败"),
    FAILD_LOGIN(444, "登录失败"),
    NOT_LOGIN(402,"暂未登录"),
    Author_ERROR(403,"用户名不存在或密码错误"),
    API_PARAMETER_ERROR(500, "参数错误"),
    UNEXPECTED_ERROR(999, "未知错误"),
    REFULSE_REQUEST(777, "拒绝访问");

    /**
     * 状态码
     */
    private int code;
    /**
     * 消息
     */
    private String msg;

    CodeEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
