package com.hwb.tg.Controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Controller
public class FileController {
    @ResponseBody
    @RequestMapping("/uploadFile")
    public Map uploadFile(HttpServletRequest request, MultipartFile file) {
        HashMap<String, Object> ret = new HashMap<>();
        if (file == null) {
            ret.put("type", "error");
            ret.put("msg", "选择要上传的文件！");
            return ret;
        }
        if (file.getSize() > 1024 * 1024 * 10) {
            ret.put("type", "error");
            ret.put("msg", "文件大小不能超过10M！");
            return ret;
        }
        //获取文件后缀
        System.out.println(System.getProperty("user.dir"));
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        String savePath = System.getProperty("user.dir") + "/file/";
        String fname = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
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
            ret.put("type", "error");
            ret.put("msg", "保存文件异常！");
            e.printStackTrace();
            return ret;
        }
        ret.put("location", "http://47.97.97.208:8082/ssm_learning/file/" + filename);
        ret.put("name", fname);
        return ret;
    }

    @RequestMapping(value = "/file/{path}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String path) throws IOException {
        String filePath = System.getProperty("user.dir") + "/file/" + path;
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

    @RequestMapping(value = "/template/{path}")
    public ResponseEntity<InputStreamResource> downloadTemplate(@PathVariable String path) throws IOException {
        String filePath = System.getProperty("user.dir") + "/template/" + path;
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
