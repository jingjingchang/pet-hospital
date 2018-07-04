package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.Questionnaire;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:43
 */

@RequestMapping("/admin/sys/questionnaire")
@Controller
public class QuestionnaireController extends BaseController<Questionnaire> {

    @Autowired
    QuestionnaireService questionnaireService;

    @Override
    public BaseService<Questionnaire> getService() {
        return questionnaireService;
    }

    @Override
    public String getViewPrefix() {
        return "questionnaire";
    }
}
