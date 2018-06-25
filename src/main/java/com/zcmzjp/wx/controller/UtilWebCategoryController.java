package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.UtilWebCategory;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.UtilWebCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/blog/utilWebCategory")
public class UtilWebCategoryController extends BaseController<UtilWebCategory> {
    @Autowired
    UtilWebCategoryService utilWebCategoryService;

    @Override
    public BaseService<UtilWebCategory> getService() {
        return utilWebCategoryService;
    }

    @Override
    public String getViewPrefix() {
        return "utilWebCategory";
    }
}
