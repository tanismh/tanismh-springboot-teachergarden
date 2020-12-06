package com.hwb.tg.Dao;

import com.hwb.tg.Bean.PermissionOfCategory;
import com.hwb.tg.pojo.CategoryReturn;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionOfCategoryDao {
    public PermissionOfCategory checkPermissionOfTeacher(Integer permissionId, Integer categoryId);
    public List<CategoryReturn> getPermissionCategoryByJobNumber(String jobNumber);
}
