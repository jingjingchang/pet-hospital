package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ExaminationAnswer;
import com.zcmzjp.wx.mapper.ExaminationAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:00
 */
@Service
public class ExaminationAnswerService extends BaseService<ExaminationAnswer> {

    @Autowired
    ExaminationAnswerMapper questionAnswerMapper;

    @Override
    public BaseMapper<ExaminationAnswer> getMapper() {
        return questionAnswerMapper;
    }
}
