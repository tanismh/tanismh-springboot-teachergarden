package com.hwb.tg.pojo;

public class NewsContentResult {
    private Integer newsId;
    private String newsTitle;
    private String department;
    private String  publishTime;
    private String className;
    private String content;
    private Integer classId;

    @Override
    public String toString() {
        return "NewsContentResult{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", department='" + department + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", className='" + className + '\'' +
                ", content='" + content + '\'' +
                ", classId=" + classId +
                '}';
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
