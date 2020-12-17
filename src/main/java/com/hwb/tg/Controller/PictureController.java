package com.hwb.tg.Controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {
    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadPhoto", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadPhoto(HttpServletRequest request, MultipartFile file) {
        Map<String, Object> ret = new HashMap<String, Object>();
        if (file == null) {
            ret.put("errno", 1);
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            ret.put("errno", 1);
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        String fname = file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."));
        if (!"jpg,jpeg,png".toUpperCase().contains(suffix.toUpperCase())) {
            ret.put("errno", 1);
            ret.put("msg", "请选择jpg,jpeg,png格式的图片！");
            return ret;
        }
        //获取项目根目录加上图片目录 webapp/static/imgages/upload/
        String savePath = System.getProperty("user.dir")+ "/picture/";
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在该目录，则创建目录
            savePathFile.mkdir();
        }
        String strDateFormat = "yyyyMMddHHmmssSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strDateFormat);
        String filename = simpleDateFormat.format(new Date()) + "." + suffix;
        try {
            //将文件保存指定目录
            file.transferTo(new File(savePath + filename));
        } catch (Exception e) {
            ret.put("errno", 1);
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("errno", 0);
        ret.put("msg", "上传图片成功！");
        ret.put("location","http://47.97.97.208:8082/ssm_learning/picture/"+filename);
        ret.put("name",fname);
        return ret;
    }

    @RequestMapping(value = "/picture/{path}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String path) throws IOException {
        String filePath = System.getProperty("user.dir") + "/picture/" + path;
        FileSystemResource file = new FileSystemResource(filePath);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getFilename()));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(file.getInputStream()));
    }
}
