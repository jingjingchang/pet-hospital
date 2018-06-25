package com.zcmzjp.wx.controller.client;

import com.zcmzjp.wx.controller.BaseController;
import com.zcmzjp.wx.entity.Article;
import com.zcmzjp.wx.service.ArticleService;
import com.zcmzjp.wx.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client/article")
public class ClientArticleController extends BaseController<Article> {

    @Autowired
    ArticleService articleService;

    @Override
    public BaseService<Article> getService() {
        return articleService;
    }

    @Override
    public String getViewPrefix() {
        return null;
    }
}
