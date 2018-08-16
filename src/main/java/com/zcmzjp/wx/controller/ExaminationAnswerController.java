package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.ExaminationAnswer;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.ExaminationAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:42
 */
@Controller
@RequestMapping("/admin/sys/examinationAnswer")
public class ExaminationAnswerController extends BaseController<ExaminationAnswer> {

    @Autowired
    ExaminationAnswerService examinationAnswerService;

    @Override
    public BaseService<ExaminationAnswer> getService() {
        return examinationAnswerService;
    }

    @Override
    public String getViewPrefix() {
        return "examinationAnswer";
    }

    @RequestMapping({"/listPageByPaperId/{paperId}"})
    @ResponseBody
    public Object listPage(Page page,@PathVariable Integer paperId)
    {
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        map.put("paperId",paperId);
        PageInfo info = getService().listByPage(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));

        return jsonMap;
    }
}
