package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Page;
import com.zcmzjp.wx.entity.Teacher;
import com.zcmzjp.wx.entity.Unit;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.TeacherService;
import com.zcmzjp.wx.service.UnitService;
import com.zcmzjp.wx.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2017-08-09.
 */
@RequestMapping("/admin/sys/car/teacher")
@Controller
public class TeacherController extends BaseController<Teacher> {
    @Autowired
    TeacherService teacherService;

    @Autowired
    UnitService unitService;

    @Override
    public BaseService<Teacher> getService() {
        return teacherService;
    }

    @Override
    public String getViewPrefix() {
        return "teacher";
    }

    @ResponseBody
    @RequestMapping("/setPwd")
    public JsonResult setPwd(Integer tId,String pwd){
        Teacher teacher = teacherService.getById(tId);
        teacher.setPwd(SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(pwd)));
        boolean flag = teacherService.update(teacher);
        return new JsonResult(flag,flag?"密码设置成功":"操作失败，请重试！");
    }

    @Override
    @RequestMapping("/add")
    public String add(String menuId, Model view) {
        List<Unit> units = unitService.getAllUnit();
        view.addAttribute("units",units);
        return getViewPrefix()+"/add";
    }

    @Override
    @RequestMapping("/edit/{id}")
    public String edit(Model view,@PathVariable Integer id) {
        List<Unit> units = unitService.getAllUnit();
        Teacher teacher = teacherService.getById(id);
        view.addAttribute("units",units);
        view.addAttribute("teacher",teacher);
        return getViewPrefix()+"/edit";
    }

    @ResponseBody
    @RequestMapping("/getTeacherListWithOutByUnitId")
    public List<Teacher> getTeacherListWithOutByUnitId(Integer unitId){
        return teacherService.getTeacherListWithOutByUnitId(unitId);
    }

    @RequestMapping({"/getTeacherExamResultList"})
    @ResponseBody
    public Object getTeacherExamResultList(Page page,String mouth)
    {
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        map.put("mouth", mouth);
        PageInfo info = teacherService.getTeacherExamResultList(map);
        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));

        return jsonMap;
    }

    @RequestMapping("/getTeacherExamResultPage")
    public String getTeacherExamResultPage(String menuId, Model view) {
        return "examinationAnswer/teacherExamResult";
    }
}
