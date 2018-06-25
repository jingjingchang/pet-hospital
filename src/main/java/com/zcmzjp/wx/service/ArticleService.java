package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Article;
import com.zcmzjp.wx.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService extends BaseService<Article> {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public BaseMapper<Article> getMapper()
    {
        return articleMapper;
    }


    public List<Article> getAllArticles()
    {
        return articleMapper.getAllArticles();
    }
}
