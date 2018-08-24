package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.WxPushMemberDto;
import com.zcmzjp.wx.entity.WxPushMember;
import com.zcmzjp.wx.mapper.WxPushMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-08-24 11:00
 */
@Service
public class WxPushMemberService extends BaseService<WxPushMember> {

    @Autowired
    WxPushMemberMapper wxPushMemberMapper;

    @Override
    public BaseMapper<WxPushMember> getMapper() {
        return wxPushMemberMapper;
    }

    public List<WxPushMemberDto> getListByTypeCode(Integer type){
        return wxPushMemberMapper.getListByTypeCode(type);
    }
}
