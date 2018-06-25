package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.WxReply;
import com.zcmzjp.wx.mapper.WxReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chris on 2017-08-10.
 */
@Service
public class WxReplyService extends BaseService<WxReply> {

    @Autowired
    WxReplyMapper wxReplyMapper;

    @Override
    public BaseMapper<WxReply> getMapper() {
        return wxReplyMapper;
    }

    public WxReply getReplyByKeyName(String name){
        return wxReplyMapper.getReplyByKeyName(name);
    }
}
