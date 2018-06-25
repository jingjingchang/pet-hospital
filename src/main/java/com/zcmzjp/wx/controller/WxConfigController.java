package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.WxConfig;
import com.zcmzjp.wx.entity.WxGraphic;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.WXGraphicService;
import com.zcmzjp.wx.service.WxConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-30 12:15
 * Description:
 */
@Controller
@RequestMapping("/admin/weChat/wxConfig")
public class WxConfigController extends BaseController<WxConfig> {

    @Autowired
    WxConfigService wxConfigService;

    @Autowired
    WXGraphicService wxGraphicService;

    @Override
    public BaseService<WxConfig> getService() {
        return wxConfigService;
    }

    @Override
    public String getViewPrefix() {
        return "wxConfig";
    }

    @Override
    @RequestMapping("/edit/{id}")
    public String edit(Model view,@PathVariable Integer id) {
        WxConfig wxConfig = wxConfigService.getById(id);
        List<WxGraphic> wxGraphics = wxGraphicService.getAllGraphics();
        view.addAttribute("graphics",wxGraphics);
        view.addAttribute("wxConfig",wxConfig);
        return getViewPrefix()+"/edit";
    }
}
