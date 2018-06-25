package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.dto.UnitTeacherDto;
import com.zcmzjp.wx.entity.UnitTeacher;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.UnitTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-24 9:29
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/unitTeacher")
public class UnitTeacherController extends BaseController<UnitTeacher>{

    @Autowired
    UnitTeacherService unitTeacherService;

    @Override
    public BaseService<UnitTeacher> getService() {
        return unitTeacherService;
    }

    @Override
    public String getViewPrefix() {
        return "unitTeacher";
    }

    @ResponseBody
    @RequestMapping("/getTeacherListByUnitId")
    public List<UnitTeacherDto> getTeacherListByUnitId(Integer unitId){
        return unitTeacherService.getTeacherListByUnitId(unitId);
    }
}
