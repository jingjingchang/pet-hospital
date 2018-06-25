package com.zcmzjp.wx.mapper;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Article;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    List<Article> getAllArticles();
}
