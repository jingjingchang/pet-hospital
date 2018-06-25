package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.OrderDetailDto;
import com.zcmzjp.wx.dto.PassDto;
import com.zcmzjp.wx.entity.Order;
import com.zcmzjp.wx.entity.OrderDetail;
import com.zcmzjp.wx.mapper.OrderDetailMapper;
import com.zcmzjp.wx.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chris on 2018-01-17.
 */
@Service
public class OrderDetailService extends BaseService<OrderDetail> {
    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public BaseMapper<OrderDetail> getMapper() {
        return orderDetailMapper;
    }


    public List<OrderDetail> getTimeListByOrderId(Integer stuId){
        return orderDetailMapper.getTimeListByOrderId(stuId);
    }
    public List<OrderDetailDto> getOrderDetailList(){
        return orderDetailMapper.getOrderDetailList();
    }

}
