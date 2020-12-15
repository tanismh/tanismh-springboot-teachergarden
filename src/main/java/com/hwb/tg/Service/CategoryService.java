package com.hwb.tg.Service;


import com.hwb.tg.Bean.Category;
import com.hwb.tg.pojo.CategoryReturn;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/11/30 18:28
 */

public interface CategoryService {
    /**
     * 获取其他类别
     *
     * @return
     */
    public List<Category> getElse();

    /**
     * 获取教师有权限修改的类目(用工号)
     *
     * @param jobNumebr
     * @return
     */
    public List<CategoryReturn> getPermissionCategory(String jobNumebr);

    /**
     * 获取管理员能发布的权限
     *
     * @param adminId 管理员ID
     * @return
     */
    public List<CategoryReturn> getPermissionCategoryByAdminId(Integer adminId);
}
