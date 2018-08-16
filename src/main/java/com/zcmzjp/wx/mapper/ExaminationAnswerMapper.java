package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ExaminationAnswer;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 9:59
 */
public interface ExaminationAnswerMapper extends BaseMapper<ExaminationAnswer> {
    ExaminationAnswer getByStudentAndOrderId(Integer stuId,Integer orderId);

    ExaminationAnswer getByLatestOrderStuId(@Param("studentId") Integer studentId);
}
