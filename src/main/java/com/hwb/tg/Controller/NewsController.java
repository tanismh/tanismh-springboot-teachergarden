package com.hwb.tg.Controller;

import com.hwb.tg.Bean.News;
import com.hwb.tg.Dao.TeacherDao;
import com.hwb.tg.Service.NewsService;
import com.hwb.tg.Service.TeacherService;
import com.hwb.tg.pojo.AdminLogin;
import com.hwb.tg.pojo.NewsContentResult;
import com.hwb.tg.pojo.NewsTitleResult;
import com.hwb.tg.pojo.TeacherLoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {

    @Autowired
    NewsService newsServiceImpl;

    @Autowired
    TeacherService teacherServiceImpl;

    @Autowired
    TeacherDao teacherDao;

    /**
     * 获取新闻标题列表
     *
     * @param newsType
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getNewsTitle", method = RequestMethod.GET)
    public Map getNewsTitle(@RequestParam("newsType") Integer newsType,
                            @RequestParam("pageSize") Integer pageSize,
                            @RequestParam("pageNumber") Integer pageNumber,
                            @RequestParam(value = "flag", required = false) Integer flag) {
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", 200);
        returnMap.put("msg", "请求成功");
        String role = null;
        try {
            role = ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getRole();
        }catch (ClassCastException e){
            role = ((AdminLogin) SecurityUtils.getSubject().getPrincipal()).getRole();
        }
        if (flag == null)
            flag = 2;
        if (role.contains("teacher"))
            returnMap.put("result", newsServiceImpl.getNewsTitleList(newsType, pageSize, pageNumber, role, ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber(), flag));
        else
            returnMap.put("result", newsServiceImpl.getNewsTitleList(newsType, pageSize, pageNumber, role, ((AdminLogin) SecurityUtils.getSubject().getPrincipal()).getAdminId()+"", flag));
        return returnMap;
    }

    /**
     * 获取新闻内容
     *
     * @param newsId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getNewsDetail", method = RequestMethod.GET)
    public Map getNewsDetail(@RequestParam("newsId") Integer newsId) {
        HashMap<String, Object> ret = new HashMap<>();
        NewsContentResult newsDetail = newsServiceImpl.getNewsDetail(newsId);
        ret.put("code", 200);
        ret.put("data", newsDetail);
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/uploadNews", method = RequestMethod.POST)
    public Map uploadNews(@RequestBody News newsInfo) {
        HashMap<String, Object> ret = new HashMap<>();
        try {
            String role = ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getRole();
            String userId = "";
            Integer categoryId = (Integer) newsInfo.getClassId();
            if (role.equals("teacher")) {
                userId = ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber(); // jobNumber
                if (teacherServiceImpl.checkTeacherPermission(userId, categoryId)) {
                    newsServiceImpl.teacherUploadNews(userId, newsInfo);
                    ret.put("code", 200);
                    ret.put("msg", "添加成功");
                } else {
                    ret.put("code", 300);
                    ret.put("msg", "权限不足");
                }
            }

        } catch (Error e) {
            ret.put("code", 300);
            ret.put("msg", "发生错误");
        }
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/searchNews", method = RequestMethod.POST)
    public Map searchNews(@RequestBody Map searchInfo) {
        HashMap<String, Object> ret = new HashMap<>();
        if (searchInfo.get("newsType") == null) {
            NewsTitleResult newsTitleResult = newsServiceImpl.searchNews(searchInfo);
            ret.put("data", newsTitleResult);
        } else {
            NewsTitleResult newsTitleResult = newsServiceImpl.searchNewsByNewsType(searchInfo);
            ret.put("data", newsTitleResult);
        }
        ret.put("code", 200);
        ret.put("msg", "搜索成功");
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteMyNewsTeacher", method = RequestMethod.POST)
    public Map deleteMyNews(@RequestBody Map deletInfo) {
        HashMap<String, Object> ret = new HashMap<>();
        String jobNumber = ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber();
        Integer teacherId = teacherDao.getTeacherIdByJobNumber(jobNumber);
        List<Integer> integerList = (List<Integer>) deletInfo.get("newsIds");
        if (!newsServiceImpl.checkDeleteMyNewsTeacher(teacherId, integerList)) {
            System.out.println(newsServiceImpl.checkDeleteMyNewsTeacher(teacherId, integerList));
            ret.put("code", 300);
            ret.put("msg", "删除失败，权限不足");
        } else {
            newsServiceImpl.deleteMyNewsTeacher(integerList);
        }
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/changeMyUp", method = RequestMethod.POST)
    public Map changeMyUp(@RequestBody Map changeInfo) {
        HashMap<String, Object> ret = new HashMap<>();
        String role = ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getRole();
        String jobNumber = ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber();
        Integer teacherId = teacherDao.getTeacherIdByJobNumber(jobNumber);
        Integer newsType = (Integer) changeInfo.get("newsType");
        Integer newsId = (Integer) changeInfo.get("newsId");
        String newsTitle = (String) changeInfo.get("newsTitle");
        String content = (String) changeInfo.get("content");
        if (role.equals("teacher")) {
            //验证修改的新闻是否存在
            List<Integer> newIds = new ArrayList<Integer>();
            newIds.add((Integer) changeInfo.get("newsId"));
            //验证是不是自己发布的
            if (newsServiceImpl.checkDeleteMyNewsTeacher(teacherId, newIds)) {
                //验证修改后的类别是不是自己能发布的
                if (newsServiceImpl.checkChangeCategory(teacherId, newsType)) {
                    newsServiceImpl.updateNews(newsId, newsTitle, newsType, content);
                    ret.put("msg", "成功");
                    ret.put("code", 200);
                } else {
                    ret.put("msg", "修改后的类别权限不足");
                    ret.put("code", 300);
                }
            } else {
                ret.put("msg", "只能修改自己发布的");
                ret.put("code", 300);
            }
        }
        return ret;
    }
}
