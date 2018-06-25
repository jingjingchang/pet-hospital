package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.WxGraphic;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.WXGraphicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Chris on 2017-08-03.
 */
@Controller
@RequestMapping("/admin/weChat/wxGraphic")
public class WxGraphicController extends BaseController<WxGraphic> {
    @Autowired
    WXGraphicService wxGraphicService;

    @Override
    public BaseService<WxGraphic> getService() {
        return wxGraphicService;
    }

    @Override
    public String getViewPrefix() {
        return "wxGraphic";
    }

    @ResponseBody
    @RequestMapping("/getAllGraphics")
    public List<WxGraphic> getAllGraphics(){
        List<WxGraphic> graphics = wxGraphicService.getAllGraphics();
        return graphics;
    }

}
