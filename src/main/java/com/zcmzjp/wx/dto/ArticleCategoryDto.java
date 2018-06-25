package com.zcmzjp.wx.dto;


import com.zcmzjp.wx.entity.ArticleCategory;

public class ArticleCategoryDto extends ArticleCategory {

    private String pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
