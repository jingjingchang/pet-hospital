package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ExaminationPaper;
import com.zcmzjp.wx.mapper.ExaminationPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:26
 */
@Service
public class ExaminationPaperService extends BaseService<ExaminationPaper> {

    @Autowired
    ExaminationPaperMapper questionnaireMapper;

    @Override
    public BaseMapper<ExaminationPaper> getMapper() {
        return questionnaireMapper;
    }
}
