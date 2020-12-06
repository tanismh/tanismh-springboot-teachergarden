package com.hwb.tg.pojo;

import java.util.List;

public class NewsTitleResult {
    private Integer categoryId;
    private String className;
    private List<NewsTitleList> newsTitleLists;
    private Integer newsListLength;

    @Override
    public String toString() {
        return "NewsTitleResult{" +
                "categoryId=" + categoryId +
                ", className='" + className + '\'' +
                ", newsTitleLists=" + newsTitleLists +
                ", newsListLength=" + newsListLength +
                '}';
    }

    public Integer getNewsListLength() {
        return newsListLength;
    }

    public void setNewsListLength(Integer newsListLength) {
        this.newsListLength = newsListLength;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<NewsTitleList> getNewsTitleLists() {
        return newsTitleLists;
    }

    public void setNewsTitleLists(List<NewsTitleList> newsTitleLists) {
        this.newsTitleLists = newsTitleLists;
    }
}
