package com.hwb.tg.Service;

import com.hwb.tg.Bean.News;
import com.hwb.tg.pojo.NewsContentResult;
import com.hwb.tg.pojo.NewsTitleResult;

import java.util.List;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/7 17:08
 */

public interface NewsService {
    /**
     * 获取新闻列表
     *
     * @param newsType
     * @param pageSize
     * @param pageNumber
     * @param flag
     * @return
     */
    public NewsTitleResult getNewsTitleList(Integer newsType,
                                            Integer pageSize,
                                            Integer pageNumber,
                                            String role,
                                            String jobNumber,
                                            Integer flag);

    /**
     * 获取新闻详情
     *
     * @param NewsId
     * @return
     */
    public NewsContentResult getNewsDetail(Integer NewsId);

    /**
     * 发布新闻
     *
     * @param jobNumber
     * @param news
     */
    public void teacherUploadNews(String jobNumber, News news);

    /**
     * 搜索新闻（标题和内容）
     *
     * @param searchInfo
     * @return
     */
    public NewsTitleResult searchNews(Map searchInfo);

    /**
     * 搜索新闻（类型）
     *
     * @param searchInfo
     * @return
     */
    public NewsTitleResult searchNewsByNewsType(Map searchInfo);

    /**
     * 删除自己发布验证
     *
     * @param teacherId
     * @param newsIds
     * @return
     */
    public Boolean checkDeleteMyNewsTeacher(Integer teacherId,
                                            List<Integer> newsIds);

    /**
     * 删除自己发布
     *
     * @param newsIds
     */
    public void deleteMyNewsTeacher(List<Integer> newsIds);

    /**
     * 更新（修改自己发布）
     *
     * @param newsId
     * @param newsTitle
     * @param newsType
     * @param content
     */
    public void updateNews(Integer newsId,
                           String newsTitle,
                           Integer newsType,
                           String content);

    /**
     * 验证是否能删除
     *
     * @param teacherId 教师Id
     * @param newsType  新闻类别
     * @return
     */
    public boolean checkChangeCategory(Integer teacherId,
                                       Integer newsType);

}
