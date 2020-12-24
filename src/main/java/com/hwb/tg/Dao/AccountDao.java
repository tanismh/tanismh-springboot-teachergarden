package com.hwb.tg.Dao;

import com.hwb.tg.pojo.AddTeacher;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 何伟斌
 * @date 2020/12/23 8:54
 */

@Mapper
@Repository
public interface AccountDao {
    /**
     * 批量添加教师
     *
     * @param teacher
     */
    public void batchAddTeacher(AddTeacher teacher);
}
