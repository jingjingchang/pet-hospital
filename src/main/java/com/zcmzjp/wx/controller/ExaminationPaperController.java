package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.ExaminationPaper;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.ExaminationPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:43
 */

@Controller
@RequestMapping("/admin/sys/examinationPaper")
public class ExaminationPaperController extends BaseController<ExaminationPaper> {

    @Autowired
    ExaminationPaperService examinationPaperService;

    @Override
    public BaseService<ExaminationPaper> getService() {
        return examinationPaperService;
    }

    @Override
    public String getViewPrefix() {
        return "examinationPaper";
    }
}
