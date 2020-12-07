package com.hwb.tg.Controller;


import com.hwb.tg.Bean.Msg;
import com.hwb.tg.Service.MsgService;
import com.hwb.tg.pojo.TeacherLoginInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MsgController {
    @Autowired
    MsgService msgServiceImpl;

    /**
     * 获取信息列表
     * @param pageSize      页大小
     * @param pageNumber    页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMsg",method = RequestMethod.GET)
    @RequiresRoles(value = {"role:teacher"})
    public Map getMsg(@RequestParam("pageSize") Integer pageSize,
                      @RequestParam("pageNumber") Integer pageNumber){
        String jobNumber = ((TeacherLoginInfo) SecurityUtils.getSubject().getPrincipal()).getJobNumber();
        List<Msg> msgList = msgServiceImpl.getMsgList(jobNumber,pageSize,pageNumber);
        HashMap<String, Object> returnMap = new HashMap<>();
        returnMap.put("code",200);
        returnMap.put("msg","查询成功");
        returnMap.put("msgList",msgList);
        return returnMap;
    }
}
