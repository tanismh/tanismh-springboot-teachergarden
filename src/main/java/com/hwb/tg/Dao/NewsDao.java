package com.hwb.tg.Dao;

import com.hwb.tg.Bean.News;
import com.hwb.tg.pojo.NewsTitleList;
import com.hwb.tg.pojo.NewsTitleResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface NewsDao {
    public NewsTitleResult getNewsTitle(Integer categoryId, Integer begin, Integer end);

    public News getNewsDetail(Integer NewsId);

    public void uploadNewsByTeacher(Integer teacherId, String newsTitle, Integer categoryId, String content);

    /**
     * 删除新闻
     *
     * @param newsIds       删除的新闻ID列表
     */
    public void deleteMyNews(List<Integer> newsIds);

    public Integer getTeacherIdByNewsId(Integer newsId);

    public NewsTitleResult getMyselfNewsTeacher(Integer categoryId, Integer begin, Integer end, Integer teahcerID);

    public List<NewsTitleList> searchALlNews(@Param("param1") String key, Integer begin, Integer end);

    public NewsTitleResult searchByNewsType(@Param("param1") String key, Integer newsType, Integer begin, Integer end);

    public List<Integer> getNewsPublicTeacherId(List<Integer> newsIds);

    public List<Integer> getCategoryPermissionOfTeacher(Integer teacherId);

    public void updateNews(Integer newsId, String newsTitle, String content, Integer newType);

    public Integer getNewsLengthByNewsType(Integer newsType);

    public Integer getNewsLengthByNewsTypeAndTeacherId(Integer newsType, Integer teacherId);

    public Integer searchALlNewsLength(@Param("param1") String key);

    public Integer searchByNewsTypeLength(@Param("param1") String key, Integer newsType);

    public NewsTitleResult getMyselfNewsAdmin(Integer categoryId, Integer begin, Integer end, Integer adminId);

    public Integer getNewsLengthByNewsTypeAndAdminId(Integer newsType, Integer teacherId);
}
