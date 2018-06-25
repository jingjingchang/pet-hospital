package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.Apply;
import com.zcmzjp.wx.service.ApplyService;
import com.zcmzjp.wx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/sys/car/apply")
@Controller
public class ApplyController extends  BaseController<Apply> {

    @Autowired
    ApplyService applyService;

    @Override
    public BaseService<Apply> getService() {
        return applyService;
    }

    @Override
    public String getViewPrefix() {
        return "apply";
    }
}
