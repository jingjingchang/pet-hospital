package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.OrderDetail;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-01 11:09
 * Description:
 */
public class OrderDetailDto extends OrderDetail {

    private Integer leftNum;

    private Integer orderNum;

    private String unitName;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getLeftNum() {
        return leftNum;
    }

    public void setLeftNum(Integer leftNum) {
        this.leftNum = leftNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
