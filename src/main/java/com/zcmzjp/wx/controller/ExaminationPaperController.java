package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.ExaminationPaper;
import com.zcmzjp.wx.entity.ExaminationQuestion;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.entity.Questions;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.ExaminationPaperService;
import com.zcmzjp.wx.service.ExaminationQuestionService;
import com.zcmzjp.wx.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:43
 */

@Controller
@RequestMapping("/admin/sys/examinationPaper")
public class ExaminationPaperController extends BaseController<ExaminationPaper> {

    @Autowired
    ExaminationPaperService examinationPaperService;

    @Autowired
    ExaminationQuestionService examinationQuestionService;

    @Override
    public BaseService<ExaminationPaper> getService() {
        return examinationPaperService;
    }

    @Override
    public String getViewPrefix() {
        return "examinationPaper";
    }

    @RequestMapping("/queryQuestions/{id}")
    public String queryQuestions(Model view, @PathVariable String id){
        view.addAttribute("paperId",id);
        return "examinationPaper/queryQuestions";
    }

    @RequestMapping("/preview/{id}")
    public String preview(Model view,@PathVariable Integer id){
        ExaminationPaper examinationPaper = examinationPaperService.getById(id);
        List<ExaminationQuestion> list = examinationQuestionService.getPaperQuestionsByPaperId(id);
        view.addAttribute("questions",list);
        view.addAttribute("paper",examinationPaper);
        return "examinationPaper/preview";
    }

    @Override
    @PostMapping({"/create"})
    @ResponseBody
    public Message create(ExaminationPaper obj) throws Exception
    {
        boolean ret = getService().add(obj);
        if(obj.getStatus()==1){
            boolean re = examinationPaperService.updateOthersById(obj.getId());
        }
        return getMessage(ret);
    }

    @PostMapping({"/update"})
    @ResponseBody
    @Override
    public Message updateDictionaryData(ExaminationPaper obj)
    {
        boolean ret = getService().update(obj);
        if(obj.getStatus()==1){
            boolean re = examinationPaperService.updateOthersById(obj.getId());
        }
        return getMessage(ret);
    }

    @RequestMapping("/checkStatisticsList/{paperId}")
    public String checkStatisticsList(Model view, @PathVariable String paperId){
        view.addAttribute("paperId",paperId);
        return "examinationPaper/checkStatisticsList";
    }
}
