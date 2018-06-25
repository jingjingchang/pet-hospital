package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.OrderDto;
import com.zcmzjp.wx.entity.InOut;
import com.zcmzjp.wx.entity.Order;
import com.zcmzjp.wx.mapper.InOutMapper;
import com.zcmzjp.wx.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2018-01-17.
 */
@Service
public class OrderService extends BaseService<Order> {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public BaseMapper<Order> getMapper() {
        return orderMapper;
    }

    public List<OrderDto> getOrderAndChildren(){
        return orderMapper.getOrderAndChildren();
    }

    public List<OrderDto> getOrderAndChildrenByUnitId(Integer unitId){
        return orderMapper.getOrderAndChildrenByUnitId(unitId);
    }


}
