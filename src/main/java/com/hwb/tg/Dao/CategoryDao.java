package com.hwb.tg.Dao;

import com.hwb.tg.Bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CategoryDao {
    public Category getCategoryNameByCategoryId(Integer categoryId);
    public List<Category> getElseCategoryByElseId(Integer elseId);
}
