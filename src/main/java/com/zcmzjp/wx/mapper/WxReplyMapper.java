package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.WxReply;
import org.apache.ibatis.annotations.Param;

public interface WxReplyMapper extends BaseMapper<WxReply> {

     WxReply getReplyByKeyName(@Param("name") String name);
}