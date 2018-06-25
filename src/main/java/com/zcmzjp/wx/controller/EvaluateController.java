package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.Evaluate;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-19 23:44
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/evaluate")
public class EvaluateController extends BaseController<Evaluate> {

    @Autowired
    EvaluateService evaluateService;

    @Override
    public BaseService<Evaluate> getService() {
        return evaluateService;
    }

    @Override
    public String getViewPrefix() {
        return "evaluate";
    }

    @RequestMapping("/list/{id}")
    public String list(Model view,@PathVariable String id) {
        view.addAttribute("teaId",id);
        return "teacher/evaluateList";
    }

    @ResponseBody
    @RequestMapping("/getEvaListByTeaId/{id}")
    public Object getJobApplyListByJobId(Page page, @PathVariable Integer id){
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        map.put("teaId",id);
        PageInfo info = getService().listByPage(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));
        return jsonMap;
    }
}
