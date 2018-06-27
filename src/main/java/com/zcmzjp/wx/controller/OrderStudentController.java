package com.zcmzjp.wx.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.dto.OrderDetailDto;
import com.zcmzjp.wx.dto.OrderStudentDto;
import com.zcmzjp.wx.entity.*;
import com.zcmzjp.wx.service.*;
import com.zcmzjp.wx.utils.DateUtils;
import com.zcmzjp.wx.utils.SendSMSUtil;
import com.zcmzjp.wx.utils.WeixinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-07 23:19
 * Description:
 */
@Controller
@RequestMapping("/admin/sys/car/orderStudent")
public class OrderStudentController extends BaseController<OrderStudent> {
    @Autowired
    OrderStudentService orderStudentService;

    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    UnitService unitService;

    @Override
    public BaseService<OrderStudent> getService() {
        return orderStudentService;
    }

    @Override
    public String getViewPrefix() {
        return "orderStudent";
    }

    @ResponseBody
    @RequestMapping("/newListByPage")
    public Object listPage(Page page,String dateTime,String subType,String unitId,String orderDetail) {
        Map<String, Object> map = new HashMap();
        map.put("pageNum", Integer.valueOf(page.getOffset() + 1));
        map.put("pageSize", Integer.valueOf(page.getLimit()));
        map.put("keyword", page.getSearch());
        map.put("dateTime", dateTime);
        map.put("subType",subType);
        map.put("unitId",unitId);
        map.put("orderDetail",orderDetail);
        PageInfo info = getService().listByPage(map);

        Map<String, Object> jsonMap = new HashMap();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", Long.valueOf(info.getTotal()));

        return jsonMap;
    }

    @RequestMapping("/newList")
    public String list(Model view) {

        List<Unit> units = unitService.getAllUnit();
        List<OrderDetailDto> orderDetails = orderDetailService.getOrderDetailList();
        view.addAttribute("units",units);
        view.addAttribute("orderDetails",orderDetails);
        return getViewPrefix() + "/list";
    }

    @ResponseBody
    @RequestMapping("/sendSMS")
    public JsonResult sendSMS(Integer id,String pickupTime,String finalPickup,String finalTime){
        finalPickup = (finalPickup==null||finalPickup=="")?"指定":finalPickup;
        boolean flag = false;
        OrderStudentDto o = orderStudentService.getOrderStudentByStuId(id);
        if(o==null){
            flag = false;
            return new JsonResult(flag,"对不起，只能只能给明天学车的学员发送短信！");
        }
        try {
            SendSmsResponse response = SendSMSUtil.sendOrderSms(o.getStuName(),o.getSmobile(),o.getTeachName(),o.getTmobile(),finalTime,pickupTime,finalPickup);
            if (response.getCode().equals("OK")){
                o.setSms(1);
                o.setFinalPickup(finalPickup);
                o.setFinalTime(finalTime);
                o.setPickupTime(pickupTime);
                flag = true;
                orderStudentService.update(o);
            }
            WeixinUtil.sendMessage(o.getWxOpenId(),"text","【征诚驾培】提醒您\n学员："+o.getStuName()+"\n您已成功预约\n师傅："+o.getTeachName()+"，电话："+o.getTmobile()+"\n明天："+finalTime+"的学车服务\n请在："+pickupTime+"前达到"+finalPickup+"接送点");
        } catch (ClientException e) {
            e.printStackTrace();
            flag = false;
        }
        return new JsonResult(flag,flag?"短信发送成功！":"短信发送失败！");
    }

    @ResponseBody
    @RequestMapping("/sendToNoOrderStudent")
    public JsonResult sendToNoOrderStudent(){
        int num=0;
        OrderStudent orderStudent;
        try {
            System.out.println("执行推送任务："+ new Date(System.currentTimeMillis()));
            List<OrderStudentDto> orderStudentDtos = orderStudentService.getOrderStudentBySmsStatus(null,null);

            for(OrderStudentDto o:orderStudentDtos) {
                WeixinUtil.sendMessage(o.getWxOpenId(),"text","【征诚驾培】提醒您\n截止："+ DateUtils.getNow()+"\n尊贵的征诚驾培学员"+o.getStuName()+"您好:\n师傅根据对您的教学安排，今天没有受理您的预约练车，请复习学习过的知识，欢迎下次预约！\n");
                orderStudent = new OrderStudent();
                orderStudent.setId(o.getId());
                orderStudent.setSms(2);
                orderStudentService.updatePart(orderStudent);
                num = num+1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(true,"成功推送："+num+"条消息");
    }

    @RequestMapping("/getOrderListByStudentId")
    @ResponseBody
    public List getOrderListByStudentId(Integer studentId){
        return orderStudentService.getOrderListByStudentId(studentId);
    }

}
