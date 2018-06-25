package com.zcmzjp.wx.dto;


import com.zcmzjp.wx.entity.UtilWeb;

public class UtilWebDto extends UtilWeb {

    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
