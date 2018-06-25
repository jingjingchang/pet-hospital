package com.zcmzjp.wx.controller.client;

import com.zcmzjp.wx.controller.BaseController;
import com.zcmzjp.wx.entity.Tag;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/tag")
public class ClientTagController extends BaseController<Tag> {
    @Autowired
    TagService tagService;

    @Override
    public BaseService<Tag> getService() {
        return tagService;
    }

    @Override
    public String getViewPrefix() {
        return null;
    }
}
