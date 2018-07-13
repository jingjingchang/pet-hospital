package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.ExaminationAnswer;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.ExaminationAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:42
 */
@Controller
@RequestMapping("/admin/sys/examinationAnswerService")
public class ExaminationAnswerController extends BaseController<ExaminationAnswer> {

    @Autowired
    ExaminationAnswerService examinationAnswerService;

    @Override
    public BaseService<ExaminationAnswer> getService() {
        return examinationAnswerService;
    }

    @Override
    public String getViewPrefix() {
        return "questionAnswer";
    }
}
