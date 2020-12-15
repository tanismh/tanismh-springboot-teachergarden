package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao {
    /**
     * 通过类目ID获取类目名字
     *
     * @param categoryId
     * @return
     */
    public Category getCategoryNameByCategoryId(Integer categoryId);

    /**
     *
     * @param elseId
     * @return
     */
    public List<Category> getElseCategoryByElseId(Integer elseId);
}
