package com.chris.pethospital.controller;


import com.chris.pethospital.config.ServerConfig;
import com.chris.pethospital.entity.JsonResult;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.jni.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

@Controller
@RequestMapping({"/upload"})
public class UploadController {
    public static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    ServerConfig serverConfig;

    @ResponseBody
    @RequestMapping(value={"/fileUpload"}, method={RequestMethod.POST})
    public JsonResult fileUpload(FileInfo info, @RequestParam("file") MultipartFile file, HttpServletRequest request)
    {
        if (file == null) {
            return new JsonResult().failure("文件不能为空！");
        }
        String path = request.getContextPath();
        String absPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath() + "static/images";
        try{
            createFileFolder(absPath, false);
        }catch (Exception e){
            return new JsonResult(false, e.getMessage());
        }
        String newFileName =  file.getOriginalFilename();
        try
        {
            File target = new File(absPath, newFileName);
            file.transferTo(target);
        }catch (Exception e){
            log.error("数据上传失败。", e);
            return new JsonResult(false, "数据上传失败。");
        }
        return new JsonResult(true,   "/images/" + newFileName);
    }

    /*@ResponseBody
    @RequestMapping(value = {"/fileUpload"}, method = {RequestMethod.POST})
    public JsonResult newsFileUpload(MultipartFile[] imgFile) {
        String absPath = this.serverConfig.getUploadPath() + "/dm/newsImage";
        Integer error = Integer.valueOf(0);
        String newFileName = null;
        try {
            createFileFolder(absPath, false);
        } catch (Exception e) {
            return new JsonResult(error, "文件上传失败", "/dm/newsImage/" + newFileName);
        }
        try {
            for (MultipartFile iFile : imgFile) {
                newFileName = iFile.getOriginalFilename();
                File uploadedFile = new File(absPath, newFileName);

                FileUtils.copyInputStreamToFile(iFile.getInputStream(), uploadedFile);
                error = Integer.valueOf(0);
            }
        } catch (Exception e) {
            error = Integer.valueOf(1);
            log.error("数据上传失败。", e);
            return new JsonResult(error, "文件上传失败", "/dm/newsImage/" + newFileName);
        }
        return new JsonResult(error, "文件上传成功", "/dm/newsImage/" + newFileName);
    }*/


    private void createFileFolder(String file, boolean hasTmp)
            throws Exception {
        File tmpFile = new File(file);
        if (!tmpFile.exists()) {
            try {
                tmpFile.mkdirs();
            } catch (Exception ex) {
                log.error("创建文件夹失败。", ex);
                throw new Exception("创建文件夹失败。", ex);
            }
        }
        if (hasTmp) {
            tmpFile = new File(file + ".tmp");
            if (tmpFile.exists()) {
                tmpFile.setLastModified(System.currentTimeMillis());
            } else {
                try {
                    tmpFile.createNewFile();
                } catch (IOException ex) {
                    log.error("无法创建tmp文件。", ex);
                    throw new Exception("创建tmp文件失败。", ex);
                }
            }
        }
    }

}
