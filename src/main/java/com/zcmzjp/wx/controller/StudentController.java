package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.Student;
import com.zcmzjp.wx.entity.Unit;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.StudentService;
import com.zcmzjp.wx.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2017-08-09.
 */
@RequestMapping("/admin/sys/car/student")
@Controller
public class StudentController extends BaseController<Student> {
    @Autowired
    StudentService studentService;

    @Autowired
    UnitService unitService;

    @Override
    public BaseService<Student> getService() {
        return studentService;
    }

    @Override
    public String getViewPrefix() {
        return "student";
    }

    @Override
    @RequestMapping("/add")
    public String add(String menuId, Model view) {

        List<Unit> list = unitService.getAllUnit();
        view.addAttribute("units",list);
        return  getViewPrefix()+"/add";
    }

    @Override
    @RequestMapping("edit/{id}")
    public String edit(Model view,@PathVariable Integer id) {
        List<Unit> list = unitService.getAllUnit();
        Student student = studentService.getById(id);
        view.addAttribute("units",list);
        view.addAttribute("student",student);
        return  getViewPrefix()+"/edit";
    }


    @ResponseBody
    @RequestMapping("/getStudentUnitStatistics")
    public List<Map<Object,Object>> getStudentUnitStatistics(){
        return studentService.getStudentUnitStatistics();
    }

    @ResponseBody
    @RequestMapping("/getStudentGenderStatistics")
    public List<Map<Object,Object>> getStudentGenderStatistics(){
        return studentService.getStudentGenderStatistics();
    }
}
