package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.Order;
import com.zcmzjp.wx.entity.OrderDetail;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-25 0:21
 * Description:
 */
public class OrderDto extends Order{

    private String uname;

    private List<OrderDetailDto> odList;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public List<OrderDetailDto> getOdList() {
        return odList;
    }

    public void setOdList(List<OrderDetailDto> odList) {
        this.odList = odList;
    }
}
