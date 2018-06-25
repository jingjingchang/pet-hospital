package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.SendTestOrder;
import com.zcmzjp.wx.entity.Tag;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.SendTestOrderService;
import com.zcmzjp.wx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sys/car/sendTestOrder")
public class SendTestOrderController extends BaseController<SendTestOrder> {

    @Autowired
    SendTestOrderService sendTestOrderService;

    @Override
    public BaseService<SendTestOrder> getService() {
        return sendTestOrderService;
    }

    @Override
    public String getViewPrefix() {
        return "sendTestOrder";
    }
}
