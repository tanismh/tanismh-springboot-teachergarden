package com.hwb.tg.Bean;

public class PermissionOfCategory {
    private Integer categoryId;
    private Integer permissionId;

    @Override
    public String toString() {
        return "PermissionOfCategory{" +
                "categoryId=" + categoryId +
                ", permissionId=" + permissionId +
                '}';
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
