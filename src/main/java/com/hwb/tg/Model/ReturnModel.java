package com.hwb.tg.Model;


/**
 * @author 何伟斌
 * @date 2020/12/6 12:00
 */

public class ReturnModel {
    /**
     * 状态码
     */
    public Integer code;
    /**
     * 消息
     */
    public String msg;
    /**
     * 返回数据
     */
    public Object data;

    /**
     * 无返回数据构造器
     * @param codeEnum
     */
    public ReturnModel(CodeEnum codeEnum){
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    /**
     * 有返回数据构造器
     * @param codeEnum
     * @param data
     */
    public ReturnModel(CodeEnum codeEnum, Object data){
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }


    /**
     * get/set方法
     * @return
     */

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
