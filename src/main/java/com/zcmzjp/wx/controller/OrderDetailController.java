package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.dto.PassDto;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.entity.Order;
import com.zcmzjp.wx.entity.OrderDetail;
import com.zcmzjp.wx.entity.OrderTime;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.OrderDetailService;
import com.zcmzjp.wx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-07 23:19
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/orderDetail")
public class OrderDetailController extends BaseController<OrderDetail> {
    @Autowired
    OrderDetailService orderDetailService;

    @Override
    public BaseService<OrderDetail> getService() {
        return orderDetailService;
    }

    @Override
    public String getViewPrefix() {
        return "orderDetailService";
    }

    @ResponseBody
    @RequestMapping("/getTimeListByOrderId")
    public List<OrderDetail> getTimeListByOrderId(Integer pId){
        List<OrderDetail> passList = orderDetailService.getTimeListByOrderId(pId);
        return passList;
    }

    @ResponseBody
    @RequestMapping("/getOrderDetail")
    public List<OrderDetail> getOrderDetail(Integer pId){
        Map<String,Object> map = new HashMap<>();
        List<OrderDetail> passList = orderDetailService.getAll(map);
        return passList;
    }


    @Override
    public Message updateDictionaryData(OrderDetail obj) {
        return getMessage(orderDetailService.updatePart(obj));
    }
}
