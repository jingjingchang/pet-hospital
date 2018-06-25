package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.Unit;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-17 15:07
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/unit")
public class UnitController extends BaseController<Unit> {

    @Autowired
    UnitService unitService;

    @Override
    public BaseService<Unit> getService() {
        return unitService;
    }

    @Override
    public String getViewPrefix() {
        return "unit";
    }

}
