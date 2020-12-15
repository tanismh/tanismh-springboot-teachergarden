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
     * @param newsIds 删除的新闻ID列表
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


    /**
     * 获取管理员自己发布的
     *
     * @param categoryId 类目ID
     * @param begin      开始页
     * @param end        结束页
     * @param adminId    管理员ID
     * @return
     */
    public NewsTitleResult getMyselfNewsAdmin(Integer categoryId, Integer begin, Integer end, Integer adminId);

    public Integer getNewsLengthByNewsTypeAndAdminId(Integer newsType, Integer teacherId);

    /**
     * 子管理员删除权限验证
     *
     * @param adminId
     * @param newsIds
     * @return
     */
    public Integer checkAdminDelPermission(Integer adminId, List<Integer> newsIds);

    /**
     * 管理员上传新闻
     *
     * @param adminId    管理员Id
     * @param newsTitle  新闻标题
     * @param categoryId 类目ID
     * @param content    新闻内容
     */
    public void adminUploadNews(Integer adminId, String newsTitle, Integer categoryId, String content);

    /**
     * 获取管理员发布的信息的长度
     *
     * @param adminId    管理员ID
     * @param categoryId 类目ID
     * @return
     */
    public Integer getAdminNewsLength(Integer adminId, Integer categoryId);

//    /**
//     * 获取管理员自己发布的新闻
//     * @param adminId       管理员Id
//     * @param categoryId    目录Id
//     * @return
//     */
//    public List<NewsTitleResult> getAdminNews(Integer adminId, Integer categoryId);
}
