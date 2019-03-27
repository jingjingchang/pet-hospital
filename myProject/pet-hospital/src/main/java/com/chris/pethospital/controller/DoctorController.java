package com.chris.pethospital.controller;


import com.chris.pethospital.entity.Doctor;
import com.chris.pethospital.service.BaseService;
import com.chris.pethospital.service.DoctorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/doctor")
public class DoctorController extends BaseController<Doctor>{

    @Autowired
    DoctorService doctorService;


    @Override
    public BaseService<Doctor> getService() {
        return doctorService;
    }

    @RequestMapping("/show")
    public String show(Model view){
        Map<String,Object> map = new HashMap<>(1);
        map.put("status",1);
        List<Doctor> doctorList = doctorService.getListByParams(map);
        view.addAttribute("doctors",doctorList);
         return  getViewPrefix()+"/view";
    }

    @RequestMapping("/detail/{id}")
    public String show(Model view,@PathVariable("id") Integer id){
        Doctor doctor = doctorService.getById(id);
        view.addAttribute("doctor",doctor);
        return  getViewPrefix()+"/detail";
    }

    @Override
    public String getViewPrefix() {
        return "doctor";
    }
}
