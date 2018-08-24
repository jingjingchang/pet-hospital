package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.WxPushMember;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-08-24 11:04
 */
public class WxPushMemberDto extends WxPushMember {

    private String memberName;

    private String wxOpenId;

    private Integer memberGender;

    private String memberMobile;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public Integer getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(Integer memberGender) {
        this.memberGender = memberGender;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }
}
