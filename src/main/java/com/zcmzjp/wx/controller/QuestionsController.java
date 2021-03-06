package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Questions;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-13 17:22
 */
@Controller
@RequestMapping("/admin/sys/question")
public class QuestionsController extends BaseController<Questions> {

    @Autowired
    QuestionsService questionsService;

    @Override
    public BaseService<Questions> getService() {
        return questionsService;
    }

    @Override
    public String getViewPrefix() {
        return "question";
    }

    @RequestMapping("/getQuestionsNotInPaper")
    @ResponseBody
    public JsonResult getQuestionsNotInPaper(String paperId){
        Object msg = questionsService.getQuestionsNotInPaper(paperId);
        return new JsonResult(true,msg);
    }
}
