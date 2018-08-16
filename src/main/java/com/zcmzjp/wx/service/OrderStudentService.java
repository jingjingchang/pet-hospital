package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.OrderStudentDto;
import com.zcmzjp.wx.entity.OrderStudent;
import com.zcmzjp.wx.mapper.OrderStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Chris on 2018-01-17.
 */
@Service
public class OrderStudentService extends BaseService<OrderStudent> {
    @Autowired
    OrderStudentMapper orderStudentMapper;

    @Override
    public BaseMapper<OrderStudent> getMapper() {
        return orderStudentMapper;
    }

    public OrderStudentDto getOrderStudentByStuId(Integer stuId){
       return orderStudentMapper.getOrderStudentByStuId(stuId);
    }

    public List getTomorrowOrderStudent(){
        return orderStudentMapper.getTomorrowOrderStudent();
    }

    public List<OrderStudentDto> getOrderStudentBySmsStatus(Integer smsStatus,Integer teacherId){
       return orderStudentMapper.getOrderStudentBySmsStatus(smsStatus,teacherId);
    }

    public List<OrderStudentDto> getOrderListByStudentId(Integer studentId){
        return orderStudentMapper.getOrderListByStudentId(studentId);
    }

    public OrderStudent getLatestOrderByStudentId(Integer id){
        return orderStudentMapper.getLatestOrderByStudentId(id);
    }

}
