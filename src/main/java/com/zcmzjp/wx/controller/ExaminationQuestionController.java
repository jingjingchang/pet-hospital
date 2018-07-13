package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.ExaminationQuestion;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.ExaminationQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-13 17:11
 */
@Controller
@RequestMapping("/admin/sys/examinationQuestion")
public class ExaminationQuestionController extends BaseController<ExaminationQuestion> {


    @Autowired
    ExaminationQuestionService examinationQuestionService;

    @Override
    public BaseService<ExaminationQuestion> getService() {
        return examinationQuestionService;
    }

    @Override
    public String getViewPrefix() {
        return "examinationQuestion";
    }
}
