package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.QuestionAnswer;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.QuestionAnswerSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:42
 */
@RequestMapping("/admin/sys/questionAnswer")
@Controller
public class QuestionAnswerController extends BaseController<QuestionAnswer> {

    @Autowired
    QuestionAnswerSevice questionAnswerSevice;

    @Override
    public BaseService<QuestionAnswer> getService() {
        return questionAnswerSevice;
    }

    @Override
    public String getViewPrefix() {
        return "questionAnswer";
    }
}
