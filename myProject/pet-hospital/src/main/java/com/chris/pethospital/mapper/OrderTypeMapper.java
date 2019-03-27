package com.chris.pethospital.mapper;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.OrderType;

import java.util.List;
import java.util.Map;

public interface OrderTypeMapper extends BaseMapper<OrderType> {

     List<Map<String,Object>> getOrderTypeStatistics();
}
