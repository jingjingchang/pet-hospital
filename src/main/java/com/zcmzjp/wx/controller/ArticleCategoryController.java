package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.ArticleCategory;
import com.zcmzjp.wx.service.ArticleCategoryService;
import com.zcmzjp.wx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/blog/articleCategory")
public class ArticleCategoryController extends BaseController<ArticleCategory> {
    @Autowired
    ArticleCategoryService articleCategoryService;

    @Override
    public BaseService<ArticleCategory> getService() {
        return articleCategoryService;
    }

    @Override
    public String getViewPrefix() {
        return "articleCategory";
    }
}
