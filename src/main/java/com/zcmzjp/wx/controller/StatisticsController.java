package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.service.InOutService;
import com.zcmzjp.wx.service.OrderStudentService;
import com.zcmzjp.wx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-07 10:31
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/statistics")
public class StatisticsController {

    @Autowired
    InOutService inOutService;

    @Autowired
    OrderStudentService orderStudentService;

    @Autowired
    StudentService studentService;

    @RequestMapping("")
    public String statistics(){
        return "statistics";
    }

    @ResponseBody
    @RequestMapping("/getLastMouthInOut")
    public JsonResult getLastMouthInOut(){
        List list = inOutService.getLastMouthInOut();
        return new JsonResult(true,list);
    }

    @ResponseBody
    @RequestMapping("/getTomorrowOrderStudent")
    public JsonResult getTomorrowOrderStudent(){
        List list = orderStudentService.getTomorrowOrderStudent();
        return new JsonResult(true,list);
    }

    @ResponseBody
    @RequestMapping("/getInOutByTime")
    public JsonResult getInOutByTime(String startTime,String endTime){
        List list = inOutService.getInOutByTime(startTime,endTime);
        return new JsonResult(true,list);
    }

    @ResponseBody
    @RequestMapping("/getStudentGenderStatistics")
    public List<Map<Object,Object>> getStudentGenderStatistics(){
        return studentService.getStudentGenderStatistics();
    }


    @ResponseBody
    @RequestMapping("/getMouthInOutByTime")
    public JsonResult getMouthInOutByTime(String startTime,String endTime){
        List list = inOutService.getMouthInOutByTime(startTime,endTime);
        return new JsonResult(true,list);
    }

    @RequestMapping("/index2")
    public String getIndex2(){
        return "index2";
    }
}
