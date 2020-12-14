package com.hwb.tg.Service.Impl;

import com.hwb.tg.Bean.News;
import com.hwb.tg.Dao.AdminDao;
import com.hwb.tg.Dao.CategoryDao;
import com.hwb.tg.Dao.NewsDao;
import com.hwb.tg.Dao.TeacherDao;
import com.hwb.tg.Service.NewsService;
import com.hwb.tg.pojo.AdminLogin;
import com.hwb.tg.pojo.NewsContentResult;
import com.hwb.tg.pojo.NewsTitleList;
import com.hwb.tg.pojo.NewsTitleResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/7 17:14
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsDao newsDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    AdminDao adminDao;

    @Override
    public NewsTitleResult getNewsTitleList(Integer newsType,
                                            Integer pageSize,
                                            Integer pageNumber,
                                            String role,
                                            String jobNumber,
                                            Integer flag) {
        NewsTitleResult newsTitle = null;
        if (flag == 1) {
            if (role.contains("teacher")) {
                newsTitle = newsDao.getMyselfNewsTeacher(
                        newsType,
                        (pageNumber - 1) * pageSize,
                        pageSize,
                        teacherDao.getTeacherIdByJobNumber(jobNumber));
                if (newsTitle == null) {
                    newsTitle = new NewsTitleResult();
                    newsTitle.setNewsListLength(0);
                } else {
                    newsTitle.setNewsListLength(newsDao.getNewsLengthByNewsTypeAndTeacherId(newsType, teacherDao.getTeacherIdByJobNumber(jobNumber)));
                }
            } else {
                newsTitle = newsDao.getMyselfNewsAdmin(
                        newsType,
                        (pageNumber - 1) * pageSize,
                        pageSize,
                        ((AdminLogin) SecurityUtils.getSubject().getSession()).getAdminId());
                if (newsTitle == null) {
                    newsTitle = new NewsTitleResult();
                    newsTitle.setNewsListLength(0);
                } else {
                    newsTitle.setNewsListLength(newsDao.getNewsLengthByNewsTypeAndAdminId(newsType, teacherDao.getTeacherIdByJobNumber(((AdminLogin) SecurityUtils.getSubject().getSession()).getAdminId() + "")));
                }
            }
        } else {
            newsTitle = newsDao.getNewsTitle(newsType, (pageNumber - 1) * pageSize, pageSize);
            if (newsTitle == null) {
                newsTitle = new NewsTitleResult();
                newsTitle.setNewsListLength(0);
            } else {
                newsTitle.setNewsListLength(newsDao.getNewsLengthByNewsType(newsType));
            }

        }

        return newsTitle;
    }

    @Override
    public NewsContentResult getNewsDetail(Integer NewsId) {
        News news = newsDao.getNewsDetail(NewsId);
        NewsContentResult newsContentResult = new NewsContentResult();
        newsContentResult.setContent(news.getContent());
        newsContentResult.setNewsId(news.getNewsId());
        newsContentResult.setPublishTime(news.getPublishTime());
        newsContentResult.setNewsTitle(news.getNewsTitle());
        newsContentResult.setClassId(news.getClassId());
        newsContentResult.setClassName(categoryDao.getCategoryNameByCategoryId(news.getClassId()).getClassName());
        if (news.getAdminId() != null) {
            newsContentResult.setDepartment(adminDao.getAdminDepartmentById(news.getAdminId()));
        } else if (news.getTeacherId() != null) {
            newsContentResult.setDepartment(teacherDao.getTeacherNameById(news.getTeacherId()));
        }
        return newsContentResult;
    }


    @Override
    public void teacherUploadNews(String jobNumber, News news) {
        Integer teacherId = teacherDao.getTeacherIdByJobNumber(jobNumber);
        newsDao.uploadNewsByTeacher(teacherId,
                news.getNewsTitle(),
                news.getClassId(),
                news.getContent());
    }

    @Override
    public NewsTitleResult searchNews(Map searchInfo) {
        Integer pageSize = (Integer) searchInfo.get("pageSize");
        Integer pageNumber = (Integer) searchInfo.get("pageNumber");
        String key = (String) searchInfo.get("key");
        List<NewsTitleList> news_list = newsDao.searchALlNews(key, (pageNumber - 1) * pageSize, pageSize);
        NewsTitleResult ret = new NewsTitleResult();
        ret.setNewsTitleLists(news_list);
        if (ret == null) {
            ret = new NewsTitleResult();
            ret.setNewsListLength(0);
        } else {
            ret.setNewsListLength(newsDao.searchALlNewsLength(key));
        }
        return ret;
    }


    @Override
    public NewsTitleResult searchNewsByNewsType(Map searchInfo) {
        Integer pageSize = (Integer) searchInfo.get("pageSize");
        Integer pageNumber = (Integer) searchInfo.get("pageNumber");
        String key = (String) searchInfo.get("key");
        Integer newsType = (Integer) searchInfo.get("newsType");
        NewsTitleResult ret = newsDao.searchByNewsType(key, newsType, (pageNumber - 1) * pageSize, pageSize);
        if (ret == null) {
            ret = new NewsTitleResult();
            ret.setNewsListLength(0);
        } else {
            ret.setNewsListLength(newsDao.searchByNewsTypeLength(key, newsType));
        }
        return ret;
    }

    @Override
    public Boolean checkDeleteMyNewsTeacher(Integer teacherId,
                                            List<Integer> newsIds) {
        List<Integer> teacherIds = newsDao.getNewsPublicTeacherId(newsIds);
        if (teacherIds.size() != 1) {
            return false;
        } else if (teacherIds.get(0) != teacherId) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteMyNewsTeacher(List<Integer> newsIds) {
        newsDao.deleteMyNews(newsIds);
    }

    @Override
    public void updateNews(Integer newsId,
                           String newsTitle,
                           Integer newsType,
                           String content) {
        newsDao.updateNews(newsId, newsTitle, content, newsType);
    }

    @Override
    public boolean checkChangeCategory(Integer teacherId,
                                       Integer newsType) {
        List<Integer> cpt = newsDao.getCategoryPermissionOfTeacher(teacherId);
        if (cpt.contains(newsType)) {
            return true;
        } else {
            return false;
        }
    }
}
