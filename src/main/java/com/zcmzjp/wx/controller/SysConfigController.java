package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.SysConfig;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-08-15 16:08
 */
@Controller
@RequestMapping("/admin/sys/sysconfig")
public class SysConfigController extends BaseController<SysConfig> {

    @Autowired
    SysConfigService sysConfigService;

    @Override
    public BaseService<SysConfig> getService() {
        return sysConfigService;
    }

    @Override
    public String getViewPrefix() {
        return "sysconfig";
    }
}
