package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Questionnaire;
import com.zcmzjp.wx.mapper.QuestionnaireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:26
 */
@Service
public class QuestionnaireService extends BaseService<Questionnaire> {

    @Autowired
    QuestionnaireMapper questionnaireMapper;

    @Override
    public BaseMapper<Questionnaire> getMapper() {
        return questionnaireMapper;
    }
}
