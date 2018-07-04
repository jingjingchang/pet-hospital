package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.QuestionAnswer;
import com.zcmzjp.wx.mapper.QuestionAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:00
 */
@Service
public class QuestionAnswerSevice extends BaseService<QuestionAnswer> {

    @Autowired
    QuestionAnswerMapper questionAnswerMapper;

    @Override
    public BaseMapper<QuestionAnswer> getMapper() {
        return questionAnswerMapper;
    }
}
