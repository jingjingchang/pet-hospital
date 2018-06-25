package com.zcmzjp.wx.mapper;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ArticleCategory;

import java.util.List;

public interface ArticleCategoryMapper extends BaseMapper<ArticleCategory> {

    List<ArticleCategory> getAllArticleCategorys();
}
