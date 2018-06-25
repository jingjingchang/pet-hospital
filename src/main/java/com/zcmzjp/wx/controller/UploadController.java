package com.zcmzjp.wx.controller;


import com.zcmzjp.wx.config.ServerConfig;
import com.zcmzjp.wx.entity.FileInfo;
import com.zcmzjp.wx.entity.JsonResult;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

@Controller
@RequestMapping({"/upload"})
public class UploadController
{
    @Autowired
    ServerConfig serverConfig;
    public static final Logger log = LoggerFactory.getLogger(UploadController.class);

    @ResponseBody
    @RequestMapping(value={"/md5Check"}, method={RequestMethod.POST})
    public JsonResult md5Check(FileInfo info)
    {
        JsonResult jr = new JsonResult(true);
        jr.putObj("ifExist", Boolean.valueOf(false));
        return jr;
    }

    @ResponseBody
    @RequestMapping(value={"/chunkCheck"}, method={RequestMethod.POST})
    public JsonResult chunkCheck(FileInfo info)
    {
        JsonResult jr = new JsonResult(true);
        return jr;
    }

    @ResponseBody
    @RequestMapping(value={"/fileUpload"}, method={RequestMethod.POST})
    public JsonResult fileUpload(FileInfo info, @RequestParam("file") MultipartFile file)
    {
        if (file == null) {
            return new JsonResult(false);
        }
        String absPath = serverConfig.getUploadPath() + info.getPath();
        try{
            createFileFolder(absPath, false);
        }catch (Exception e){
            return new JsonResult(false, e.getMessage());
        }
        String uniqueCode = null;
        String newFileName = "";
        try{
            uniqueCode = generateUniqueCode(info);
        }catch (Exception e){
            return new JsonResult(false, e.getMessage());
        }
        if (info.getChunks() > 0)
        {
            absPath = absPath + File.separator + uniqueCode;
            try{
                createFileFolder(absPath, true);
            }catch (Exception e){
                return new JsonResult(false, e.getMessage());
            }
            newFileName = String.valueOf(info.getChunk());
        }
        else
        {
            newFileName = file.getOriginalFilename();
        }
        try
        {
            File target = new File(absPath, newFileName);
            file.transferTo(target);

            String accessFile = "chmod a+r " + target.getAbsolutePath();
            try
            {
                Properties prop = System.getProperties();
                String os = prop.getProperty("os.name");
                if ((os != null) && (os.toLowerCase().indexOf("windows") == -1)) {
                    Runtime.getRuntime().exec(accessFile);
                }
            }
            catch (Exception localException1) {}
        }catch (Exception e){
            log.error("数据上传失败。", e);
            return new JsonResult(false, "数据上传失败。");
        }
        return new JsonResult(true, info.getPath() + "/" + newFileName);
    }

    @ResponseBody
    @RequestMapping(value={"/newsFileUpload"}, method={RequestMethod.POST})
    public JsonResult newsFileUpload(MultipartFile[] imgFile)
    {
        String absPath = this.serverConfig.getUploadPath() + "/dm/newsImage";
        Integer error = Integer.valueOf(0);
        String newFileName = null;
        try
        {
            createFileFolder(absPath, false);
        } catch (Exception e){
            return new JsonResult(error, "文件上传失败", "/dm/newsImage/" + newFileName);
        }
        try
        {
            for (MultipartFile iFile : imgFile)
            {
                newFileName = iFile.getOriginalFilename();
                File uploadedFile = new File(absPath, newFileName);

                FileUtils.copyInputStreamToFile(iFile.getInputStream(), uploadedFile);
                error = Integer.valueOf(0);
            }
        }catch (Exception e){
            error = Integer.valueOf(1);
            log.error("数据上传失败。", e);
            return new JsonResult(error, "文件上传失败", "/dm/newsImage/" + newFileName);
        }
        return new JsonResult(error, "文件上传成功", "/dm/newsImage/" + newFileName);
    }

    @ResponseBody
    @RequestMapping(value={"/chunksMerge"}, method={RequestMethod.POST})
    public JsonResult chunksMerge(FileInfo info)
    {
        JsonResult jr = new JsonResult(true);
        return jr;
    }

    private void createFileFolder(String file, boolean hasTmp)
            throws Exception
    {
        File tmpFile = new File(file);
        if (!tmpFile.exists()) {
            try
            {
                tmpFile.mkdirs();
            }catch (Exception ex){
                log.error("创建文件夹失败。", ex);
                throw new Exception("创建文件夹失败。", ex);
            }
        }
        if (hasTmp)
        {
            tmpFile = new File(file + ".tmp");
            if (tmpFile.exists()) {
                tmpFile.setLastModified(System.currentTimeMillis());
            } else {
                try
                {
                    tmpFile.createNewFile();
                }catch (IOException ex){
                    log.error("无法创建tmp文件。", ex);
                    throw new Exception("创建tmp文件失败。", ex);
                }
            }
        }
    }

    private String md5(String content)
            throws Exception
    {
        StringBuffer sb = new StringBuffer();
        try
        {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(content.getBytes("UTF-8"));
            byte[] tmpFolder = md5.digest();
            for (int i = 0; i < tmpFolder.length; i++) {
                sb.append(Integer.toString((tmpFolder[i] & 0xFF) + 256, 16)
                        .substring(1));
            }
            return sb.toString();
        }catch (NoSuchAlgorithmException ex){
            log.error("无法生成文件的MD5签名", ex);
            throw new Exception("无法生成文件的MD5签名.", ex);
        }catch (UnsupportedEncodingException ex){
            log.error("无法生成文件的MD5签名", ex);
            throw new Exception("无法生成文件的MD5签名.", ex);
        }
    }

    private String generateUniqueCode(FileInfo info)
            throws Exception
    {
        try{
            return md5(info.getName() + info.getType() + info
                    .getLastModifiedDate() + info.getSize());
        }catch (Exception e) {
            log.error("生成唯一名称失败。");
            throw new Exception("生成唯一名称失败。", e);
        }
    }
}
