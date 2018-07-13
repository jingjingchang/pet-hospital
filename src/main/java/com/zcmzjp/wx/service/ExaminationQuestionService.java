package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ExaminationQuestion;
import com.zcmzjp.wx.mapper.ExaminationQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-13 16:44
 */
@Service
public class ExaminationQuestionService extends BaseService<ExaminationQuestion> {

    @Autowired
    ExaminationQuestionMapper examinationQuestionMapper;

    @Override
    public BaseMapper<ExaminationQuestion> getMapper() {
        return examinationQuestionMapper;
    }
}
