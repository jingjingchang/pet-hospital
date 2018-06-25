package com.zcmzjp.wx.controller.client;

import com.zcmzjp.wx.controller.BaseController;
import com.zcmzjp.wx.entity.UtilWeb;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.UtilWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/client/utilWeb")
public class ClientUtilWebController extends BaseController<UtilWeb> {

    @Autowired
    UtilWebService utilWebService;

    @Override
    public BaseService<UtilWeb> getService() {
        return utilWebService;
    }

    @Override
    public String getViewPrefix() {
        return null;
    }
}
