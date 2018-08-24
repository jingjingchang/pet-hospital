package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.WxPushMemberDto;
import com.zcmzjp.wx.entity.WxPushMember;

import java.util.List;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-08-24 10:59
 */
public interface WxPushMemberMapper extends BaseMapper<WxPushMember> {
    List<WxPushMemberDto> getListByTypeCode(Integer type);
}
