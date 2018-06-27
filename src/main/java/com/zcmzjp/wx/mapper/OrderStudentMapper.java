package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.OrderStudentDto;
import com.zcmzjp.wx.entity.OrderStudent;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2018-01-17.
 */
public interface OrderStudentMapper extends BaseMapper<OrderStudent> {

    OrderStudentDto getOrderStudentByStuId(@Param("stuId") Integer stuId);

    List<Map<String,Object>> getTomorrowOrderStudent();

    List<OrderStudentDto> getOrderStudentBySmsStatus(@Param("smsStatus") Integer smsStatus,@Param("teacherId") Integer teacherId);

    List<OrderStudentDto> getOrderListByStudentId(@Param("studentId") Integer studentId) ;
}
