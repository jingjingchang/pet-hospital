package com.zcmzjp.wx.controller.client;

import com.zcmzjp.wx.dto.OrderStudentDto;
import com.zcmzjp.wx.service.OrderStudentService;
import com.zcmzjp.wx.utils.DateUtils;
import com.zcmzjp.wx.utils.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-13 14:58
 * Description:
 */
@Component
public class OrderTask {

    @Autowired
    OrderStudentService orderStudentService;

    @Scheduled(cron = "0 0 20 * * ?")
    public void sendToBoss() throws Exception{
        System.out.println("执行定时任务测试："+ new Date(System.currentTimeMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List list = orderStudentService.getTomorrowOrderStudent();
        JSONArray ja = JSONArray.fromObject(list.toString());
        String onum = ja.getJSONObject(0).getString("num");
        String date = sdf.format(DateUtils.getBeginDayOfTomorrow());
        WeixinUtil.sendMessage("o2zgNs43UzOgbAYyoq_nZ7ChWEjE","text","【征诚驾培】提醒您\n截止："+ DateUtils.getNow()+"\n明日："+date+"\n预约学车人数："+onum+"\n请做好安排后及时发送学车短信通知学员。");
        WeixinUtil.sendMessage("o2zgNs89Bku73OUz_jB6SQ5Q7G9k","text","【征诚驾培】提醒您\n截止："+ DateUtils.getNow()+"\n明日："+date+"\n预约学车人数："+onum+"\n请做好安排后及时发送学车短信通知学员。");
        WeixinUtil.sendMessage("o2zgNswDAYYNDPPhIvkZkyI28Lbo","text","【征诚驾培】提醒您\n截止："+ DateUtils.getNow()+"\n明日："+date+"\n预约学车人数："+onum+"\n请做好安排后及时发送学车短信通知学员。");
    }
}
