package com.hwb.tg.Bean;

/**
 * @author 何伟斌
 * @date 2020/11/29 10:48
 */

public class Permission {
    /**
     * 权限id
     */
    private Integer permissionId;
    /**
     * 权限描述
     */
    private String permissionDescribe;
    /**
     * 角色名
     */
    private String role;

    @Override
    public String toString() {
        return "Permission{" +
                "permissionId=" + permissionId +
                ", permissionDescribe='" + permissionDescribe + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionDescribe() {
        return permissionDescribe;
    }

    public void setPermissionDescribe(String permissionDescribe) {
        this.permissionDescribe = permissionDescribe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
