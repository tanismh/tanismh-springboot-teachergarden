package com.hwb.tg.pojo;

public class NewsTitleList {
    private Integer newId;
    private String newsTitle;
    private String newsTime;
    private boolean isRead =true;

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime.split(" ")[0];
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "NewsTitleResult{" +
                "newId=" + newId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
