package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.Email;
import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.EmailService;
import com.zcmzjp.wx.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 13:50
 */
@Controller
@RequestMapping("/admin/sys/email")
public class EmailController extends BaseController<Email>{

    @Autowired
    EmailService emailService;

    @Autowired
    MailSendService mailSendService;

    @Override
    public BaseService<Email> getService() {
        return emailService;
    }

    @Override
    public String getViewPrefix() {
        return "email";
    }

    @Override
    @ResponseBody
    @RequestMapping("/create")
    public Message create(Email obj) throws Exception {
        Message msg = new Message();
        obj.setSender("chris@zhouhongxing.cn");
        obj.setStatus(1);
        obj.setId(null);
        boolean flag = emailService.add(obj);
        if (flag){
            mailSendService.sendHtmlEmail(obj.getAddressee(),obj.getSubject(),obj.getContent());
            msg.setMessage("发送成功");
            msg.setStatus(true);
            return  msg;
        }else{
            msg.setMessage("发送失败！");
            msg.setStatus(false);
            return  msg;
        }
    }
}
