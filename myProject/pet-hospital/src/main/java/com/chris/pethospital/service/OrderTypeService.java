package com.chris.pethospital.service;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.OrderType;
import com.chris.pethospital.mapper.OrderTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderTypeService extends BaseService<OrderType> {

    @Autowired
    OrderTypeMapper orderTypeMapper;

    @Override
    public BaseMapper<OrderType> getMapper() {
        return orderTypeMapper;
    }

    public Object getOrderTypeStatistics(){
        return orderTypeMapper.getOrderTypeStatistics();
    }
}
