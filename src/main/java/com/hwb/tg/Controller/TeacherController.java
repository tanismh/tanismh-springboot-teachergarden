package com.hwb.tg.Controller;

import com.hwb.tg.Service.TeacherService;
import com.hwb.tg.pojo.TeacherLoginInfo;
import com.hwb.tg.pojo.UpdateTeacherInfoParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherServiceImpl;

    /**
     * 获取教师的全部信息
     *
     * @return
     */
    @ResponseBody
    @RequiresRoles("role:teacher")
    @RequestMapping(value = "/getTeacherInfo", method = RequestMethod.POST)
    public Map getTeacherInfo() {
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("info", (TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal());
        return returnMap;
    }


    @ResponseBody
    @RequiresRoles("role:teacher")
    @RequestMapping(value = "/teacherResetPsw", method = RequestMethod.POST)
    public Map teacherResetPsw(@RequestBody Map info) {
        HashMap<String, Object> ret = new HashMap<>();
        String newsPsw = (String) info.get("newPsw");
        String confirmPsw = (String) info.get("confirmPsw");
        String oldPsw = (String) info.get("oldPsw");
        if (teacherServiceImpl.checkTeacherPsw(((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber(), oldPsw)) {
            if (newsPsw.equals(confirmPsw)) {
                if (newsPsw == "") {
                    ret.put("code", 303);
                    ret.put("msg", "密码不能为空");
                } else {
                    teacherServiceImpl.checkTeacherPsw(((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber(), newsPsw);
                    ret.put("code", 200);
                    ret.put("msg", "修改成功");
                }
            } else {
                ret.put("code", 302);
                ret.put("msg", "两次密码不一致");
            }
        } else {
            ret.put("code", 301);
            ret.put("msg", "旧密码不正确");
        }
        return ret;
    }


    @ResponseBody
    @RequestMapping(value = "/changeInfo", method = RequestMethod.POST)
    public Map changeInfo(@RequestBody UpdateTeacherInfoParam updateTeacherInfoParam) {
        HashMap<String, Object> ret = new HashMap<>();
        updateTeacherInfoParam.setJobNumber(((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber());
        teacherServiceImpl.updateInfo(updateTeacherInfoParam);
        ret.put("code", 200);
        ret.put("msg", "修改成功");
        return ret;
    }

}
