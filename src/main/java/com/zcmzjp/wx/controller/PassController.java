package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.dto.PassDto;
import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.entity.Pass;
import com.zcmzjp.wx.entity.Student;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.PassService;
import com.zcmzjp.wx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Chris on 2017-08-09.
 */
@RequestMapping("/admin/sys/car/pass")
@Controller
public class PassController extends BaseController<Pass> {

    @Autowired
    PassService passService;

    @Autowired
    StudentService studentService;

    @Override
    public BaseService<Pass> getService() {
        return passService;
    }

    @Override
    public String getViewPrefix() {
        return "pass";
    }

    @ResponseBody
    @RequestMapping("/getPassListByStuId")
    public List<PassDto> getPassListByStuId(Integer id){
        List<PassDto> passList = passService.getListByStuId(id);
        return passList;
    }


    @Override
    public Message create(Pass obj) throws Exception {
        Student student = new Student();
        student.setId(obj.getStuId());
        if (obj.getIsPass()==1) {
            if(obj.getSubType()==4){
                student.setStudyStatus(5);
            }else{
                student.setStudyStatus(obj.getSubType()+1);
            }
        }else{
            student.setStudyStatus(obj.getSubType());
        }
        studentService.updatePart(student);
        return super.create(obj);
    }
}
