package com.hwb.tg.pojo;

public class CategoryReturn {
    private Integer categoryId;
    private String className;

    @Override
    public String toString() {
        return "CategoryReturn{" +
                "categoryId=" + categoryId +
                ", className='" + className + '\'' +
                '}';
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
}
