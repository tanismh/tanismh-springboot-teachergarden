package com.hwb.tg.Bean;

public class Msg {
    private Integer msgId;
    private String msgContent;
    private String teacherId;
    private String msgTime;
    private Integer month;
    private Integer year;

    @Override
    public String toString() {
        return "Msg{" +
                "msgId=" + msgId +
                ", msgContent='" + msgContent + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", msgTime='" + msgTime + '\'' +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMsgId() {
        return msgId;
    }

    public void setMsgId(Integer msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(String msgTime) {
        this.msgTime = msgTime.split(" ")[0];
    }
}
