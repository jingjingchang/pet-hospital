package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.OrderDto;
import com.zcmzjp.wx.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Chris on 2018-01-17.
 */
public interface OrderMapper extends BaseMapper<Order> {

     List<OrderDto> getOrderAndChildren();

     List<OrderDto> getOrderAndChildrenByUnitId(@Param("unitId") Integer unitId);

}
