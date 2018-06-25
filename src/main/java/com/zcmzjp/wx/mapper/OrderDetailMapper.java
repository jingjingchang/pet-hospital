package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.OrderDetailDto;
import com.zcmzjp.wx.dto.PassDto;
import com.zcmzjp.wx.entity.OrderDetail;

import java.util.List;

/**
 * Created by Chris on 2018-01-17.
 */
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

    List<OrderDetail> getTimeListByOrderId(Integer pId);

    List<OrderDetailDto> getOrderDetailList();
}
