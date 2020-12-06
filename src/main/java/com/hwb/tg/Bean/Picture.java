package com.hwb.tg.Bean;

public class Picture {
    private Integer picId;
    private String picUrl;

    @Override
    public String toString() {
        return "Picture{" +
                "picId=" + picId +
                ", picUrl='" + picUrl + '\'' +
                '}';
    }

    public Integer getPicId() {
        return picId;
    }

    public void setPicId(Integer picId) {
        this.picId = picId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
