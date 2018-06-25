package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.entity.OrderTime;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.OrderTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:42
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/orderTime")
public class OrderTimeController extends BaseController<OrderTime> {

    @Autowired
    OrderTimeService orderTimeService;

    @Override
    public BaseService<OrderTime> getService() {
        return orderTimeService;
    }

    @Override
    public String getViewPrefix() {
        return "orderTime";
    }

}
