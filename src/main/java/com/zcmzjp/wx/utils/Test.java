package com.zcmzjp.wx.utils;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-05 21:01
 * Description:
 */
public class Test {

    public static void main(String[] args){
        System.out.println("111");
        try {
            SendSmsResponse response = SendSMSUtil.sendSms();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
