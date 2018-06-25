package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.entity.PartTimeJob;
import com.zcmzjp.wx.entity.PartTimeJobApply;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.PartTimeJobApplyService;
import com.zcmzjp.wx.service.PartTimeJobService;
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
 * Created by: Chris on 2018-02-27 21:44
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/partTimeJobApply")
public class PartTimeJobApplyController extends BaseController<PartTimeJobApply> {

    @Autowired
    PartTimeJobApplyService partTimeJobApplyService;

    @Autowired
    PartTimeJobService partTimeJobService;

    @Override
    public BaseService<PartTimeJobApply> getService() {
        return partTimeJobApplyService;
    }

    @Override
    public String getViewPrefix() {
        return "partTimeJobApply";
    }

    @ResponseBody
    @RequestMapping("/getJobApplyListByJobId/{id}")
    public Object getJobApplyListByJobId(Page page,@PathVariable Integer id){
            Map<String, Object> map = new HashMap();
            map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
            map.put("pageSize", Integer.valueOf(page.getLimit()));
            map.put("keyword", page.getSearch());
            map.put("jId",id);
            PageInfo info = getService().listByPage(map);

            Map<String, Object> jsonMap = new HashMap();
            jsonMap.put("rows", info.getList());
            jsonMap.put("total", Long.valueOf(info.getTotal()));
            return jsonMap;
    }

    @RequestMapping("/getApplyListPage/{id}")
    public String getApplyListPage(Model view,@PathVariable Integer id){
        view.addAttribute("ptId",id);
        return "partTimeJobApply/list";
    }

    @ResponseBody
    @RequestMapping("/scoreStu")
    public JsonResult scoreStu(Integer id, Double score){
        boolean flag;
        PartTimeJobApply jobApply = partTimeJobApplyService.getById(id);
        jobApply.setScore(score);
        flag = partTimeJobApplyService.update(jobApply);
        return new JsonResult(flag,flag?"评分成功！":"评分失败！");
    }

}
