package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.WxGraphic;
import com.zcmzjp.wx.entity.WxReply;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.WXGraphicService;
import com.zcmzjp.wx.service.WxReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by Chris on 2017-08-10.
 */
@Controller
@RequestMapping("/admin/weChat/wxReply")
public class WxReplyController extends BaseController<WxReply> {
    @Autowired
    WxReplyService wxReplyService;

    @Autowired
    WXGraphicService wxGraphicService;
    @Override
    public BaseService<WxReply> getService() {
        return wxReplyService;
    }

    @Override
    public String getViewPrefix() {
        return "wxReply";
    }

    @Override
    public String add(String menuId, Model view) {
        List<WxGraphic> graphicList =  wxGraphicService.getAllGraphics();
        view.addAttribute("graphics",graphicList);
        return super.add(menuId, view);
    }

    @Override
    public String edit(Model view, @PathVariable Integer id) {
        WxReply wxReply = wxReplyService.getById(id);
        List<WxGraphic> graphicList =  wxGraphicService.getAllGraphics();
        view.addAttribute("graphics",graphicList);
        view.addAttribute("wxReply",wxReply);
        return super.edit(view, id);
    }
}
