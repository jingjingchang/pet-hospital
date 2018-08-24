package com.zcmzjp.wx.controller;

import com.zcmzjp.wx.entity.WxPushMember;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.WxPushMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-08-24 11:02
 */

@Controller
@RequestMapping("/admin/sys/wxPushMember")
public class WxPushMemberController extends BaseController<WxPushMember>{

    @Autowired
    WxPushMemberService wxPushMemberService;

    @Override
    public BaseService<WxPushMember> getService() {
        return wxPushMemberService;
    }

    @Override
    public String getViewPrefix() {
        return "wxPushMember";
    }
}
