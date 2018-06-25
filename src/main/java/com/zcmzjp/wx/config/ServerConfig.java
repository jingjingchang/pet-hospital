package com.zcmzjp.wx.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author Administrator
 * @date 2017/7/28.
 */
@Component
public class ServerConfig {

    @Value("${serverconfig.uploadPath}")
    private  String uploadPath;

    public String getUploadPath() {
        return uploadPath;
    }


}
