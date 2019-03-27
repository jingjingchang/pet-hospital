package com.chris.pethospital.controller;

import com.chris.pethospital.entity.OrderType;
import com.chris.pethospital.service.BaseService;
import com.chris.pethospital.service.OrderTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/ordertype")
@Controller
public class OrderTypeController extends BaseController<OrderType> {

    @Autowired
    OrderTypeService orderTypeService;

    @Override
    public BaseService<OrderType> getService() {
        return orderTypeService;
    }

    @Override
    public String getViewPrefix() {
        return "ordertype";
    }

    @RequestMapping("/getOrderTypeStatistics")
    @ResponseBody
    private Object getOrderTypeStatistics(){
       return orderTypeService.getOrderTypeStatistics();
    }

    @RequestMapping("/statistic")
    private Object statistic(){
       return getViewPrefix()+"/statistics";
    }


}
