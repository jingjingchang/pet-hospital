package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.OrderTime;
import com.zcmzjp.wx.mapper.OrderTimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:23
 * Description:
 */
@Service
public class OrderTimeService extends BaseService<OrderTime> {
    @Autowired
    OrderTimeMapper orderTimeMapper;

    @Override
    public BaseMapper<OrderTime> getMapper() {
        return orderTimeMapper;
    }

}
