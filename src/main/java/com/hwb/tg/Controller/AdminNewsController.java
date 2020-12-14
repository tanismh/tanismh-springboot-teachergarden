package com.hwb.tg.Controller;

import com.hwb.tg.Bean.Admin;
import com.hwb.tg.Bean.News;
import com.hwb.tg.Model.CodeEnum;
import com.hwb.tg.Model.ReturnModel;
import com.hwb.tg.Service.NewsService;
import com.hwb.tg.pojo.AdminLogin;
import com.hwb.tg.pojo.NewsTitleResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author 何伟斌
 * @date 2020/12/14 14:54
 */

@RestController
@RequestMapping("/admin")
public class AdminNewsController {

    @Autowired
    NewsService newsServiceImpl;

    /**
     * 获取管理员自己发布
     *
     * @param newsType   新闻类型
     * @param pageSize   页面大小
     * @param pageNumber 页码
     * @return
     */
    @RequestMapping("/getMyNews")
    @RequiresRoles(value = {"role:admin", "role:bigAdmin"}, logical = Logical.OR)
    public ReturnModel getMyNews(@RequestParam("newsType") Integer newsType,
                                 @RequestParam("pageSize") Integer pageSize,
                                 @RequestParam("pageNumber") Integer pageNumber) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        NewsTitleResult newsTitleList = newsServiceImpl.getNewsTitleList(newsType, pageSize, pageNumber, "admin", "", 1);
        newsTitleList.setNewsListLength(newsServiceImpl.getAdminNewsLength(((AdminLogin)SecurityUtils.getSubject().getPrincipal()).getAdminId(),newsType));
        ret.setData(newsTitleList);
        return ret;
    }

    /**
     * 管理员删除自己发布
     *
     * @param deleteInfo 删除新闻的信息（需要含有List<Integer> newsIds)
     * @returne
     */
    @RequestMapping("/deleteNews")
    @RequiresRoles(value = {"role:admin", "role:bigAdmin"}, logical = Logical.OR)
    public ReturnModel deleteNews(@RequestBody Map deleteInfo) {
        ReturnModel ret = null;
        try {
            List<Integer> newsIds = (List<Integer>) deleteInfo.get("newIds");
            if (newsServiceImpl.checkDeletePermissionAdmin(((AdminLogin) SecurityUtils.getSubject().getPrincipal()).getAdminId(), newsIds)) {
                newsServiceImpl.deleteMyNewsTeacher(newsIds);
                ret = new ReturnModel(CodeEnum.SUCCESS);
            } else {
                ret = new ReturnModel(CodeEnum.FAILD);
                ret.setMsg("权限不足");
            }
        } catch (Error e) {
            ret = new ReturnModel(CodeEnum.FAILD);
        } catch (Exception e) {
            ret = new ReturnModel(CodeEnum.FAILD);
        }
        return ret;
    }

    /**
     * 管理员上传新闻
     *
     * @param newsInfo 新闻信息
     * @return
     */
    @RequestMapping("/uploadNews")
    @RequiresRoles(value = {"role:admin", "role:bigAdmin"}, logical = Logical.OR)
    public ReturnModel uploadNews(@RequestBody News newsInfo) {
        ReturnModel ret = new ReturnModel(CodeEnum.SUCCESS);
        newsServiceImpl.adminUploadNews(((AdminLogin) SecurityUtils.getSubject().getPrincipal()).getAdminId(), newsInfo);
        return ret;
    }

}
