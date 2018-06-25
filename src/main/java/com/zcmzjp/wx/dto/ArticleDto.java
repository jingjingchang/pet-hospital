package com.zcmzjp.wx.dto;


import com.zcmzjp.wx.entity.Article;

public class ArticleDto extends Article {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
