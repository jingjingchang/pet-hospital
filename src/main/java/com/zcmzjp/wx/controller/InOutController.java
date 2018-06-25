package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.InOut;
import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.InOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-07 23:19
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/inout")
public class InOutController extends BaseController<InOut> {
    @Autowired
    InOutService inOutService;

    @Override
    public BaseService<InOut> getService() {
        return inOutService;
    }

    @Override
    public String getViewPrefix() {
        return "inout";
    }

  }
