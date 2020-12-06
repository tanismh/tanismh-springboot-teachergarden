package com.hwb.tg.pojo;

import java.util.List;

public class NewsSearchAll {
    private List<NewsTitleList> newsTitleLists;
    private Integer newsListLength;

    @Override
    public String toString() {
        return "NewsSearchAll{" +
                "newsTitleLists=" + newsTitleLists +
                ", newsListLength=" + newsListLength +
                '}';
    }

    public List<NewsTitleList> getNewsTitleLists() {
        return newsTitleLists;
    }

    public void setNewsTitleLists(List<NewsTitleList> newsTitleLists) {
        this.newsTitleLists = newsTitleLists;
    }

    public Integer getNewsListLength() {
        return newsListLength;
    }

    public void setNewsListLength(Integer newsListLength) {
        this.newsListLength = newsListLength;
    }
}
