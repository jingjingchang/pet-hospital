package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Questions;
import com.zcmzjp.wx.mapper.QuestionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-13 16:45
 */
@Service
public class QuestionsService extends BaseService<Questions> {

    @Autowired
    QuestionsMapper questionsMapper;

    @Override
    public BaseMapper<Questions> getMapper() {
        return questionsMapper;
    }
}
