package com.hwb.tg.Service.Impl;


import com.hwb.tg.Bean.Category;
import com.hwb.tg.Dao.CategoryDao;
import com.hwb.tg.Dao.PermissionOfCategoryDao;
import com.hwb.tg.Service.CategoryService;
import com.hwb.tg.pojo.CategoryReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 何伟斌
 * @date 2020/11/30 23:50
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    PermissionOfCategoryDao permissionOfCategoryDao;

    @Override
    public List<Category> getElse() {
        return categoryDao.getElseCategoryByElseId(9);
    }

    @Override
    public List<CategoryReturn> getPermissionCategory(String jobNumebr) {
        return permissionOfCategoryDao.getPermissionCategoryByJobNumber(jobNumebr);
    }
}
