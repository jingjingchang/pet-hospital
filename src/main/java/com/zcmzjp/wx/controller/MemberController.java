package com.zcmzjp.wx.controller;

import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.dto.MemberDto;
import com.zcmzjp.wx.entity.Member;
import com.zcmzjp.wx.entity.Message;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2017-08-09.
 */
@RequestMapping("/admin/sys/member")
@Controller
public class MemberController extends BaseController<Member> {
    @Autowired
    MemberService memberService;

    @Override
    public BaseService<Member> getService() {
        return memberService;
    }

    @Override
    public String getViewPrefix() {
        return "member";
    }


    @RequestMapping("/add")
    @Override
    public String add(String menuId, Model view) {
        return "member/add";
    }

    //进入编辑页面
    @Override
    @RequestMapping("/edit/{id}")
    public String edit(Model view, @PathVariable Integer id) {
        Member mem = memberService.getById(id);
        view.addAttribute(getViewPrefix(),mem);
        return getViewPrefix() + "/edit";
    }

    @RequestMapping("/getAllTrueMembers")
    @ResponseBody
    public List<Member> getAllTrueMembers(){
        List<Member> members = memberService.getAllTrueMembers();
        return members;
    }

    @Override
    public Message updateDictionaryData(Member obj) {
        Member member = memberService.getById(obj.getId());
        obj.setWxopenid(member.getWxopenid());
        obj.setCreated(member.getCreated());
        obj.setWorkUnit(member.getWorkUnit());
        obj.setCancelDate(member.getCancelDate());
        return super.updateDictionaryData(obj);
    }
}
