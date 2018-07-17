package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.ExaminationQuestion;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.ExaminationQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping("/listPageByPaperId/{id}")
    @ResponseBody
    public Object listPageByPaperId(Page page, @PathVariable String id){
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        map.put("paperId", id);
        PageInfo info = getService().listByPage(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));
        return jsonMap;
    }
}
