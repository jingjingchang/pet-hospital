package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ArticleCategory;
import com.zcmzjp.wx.mapper.ArticleCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCategoryService extends BaseService<ArticleCategory> {

    @Autowired
    ArticleCategoryMapper articleCategoryMapper;

    @Override
    public BaseMapper<ArticleCategory> getMapper() {
        return articleCategoryMapper;
    }

    public List<ArticleCategory> getAllArticleCategorys(){
        return null;
    }
}
