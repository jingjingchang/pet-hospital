package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.PartTimeJob;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.PartTimeJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:44
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/partTimeJob")
public class PartTimeJobController extends BaseController<PartTimeJob> {

    @Autowired
    PartTimeJobService partTimeJobService;

    @Override
    public BaseService<PartTimeJob> getService() {
        return partTimeJobService;
    }

    @Override
    public String getViewPrefix() {
        return "partTimeJob";
    }
}
