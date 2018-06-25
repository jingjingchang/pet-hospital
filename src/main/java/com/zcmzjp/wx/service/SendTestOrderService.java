package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.SendTestOrder;
import com.zcmzjp.wx.mapper.SendTestOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendTestOrderService extends BaseService<SendTestOrder> {

    @Autowired
    private SendTestOrderMapper sendTestOrderMapper;

    @Override
    public BaseMapper<SendTestOrder> getMapper() {
        return sendTestOrderMapper;
    }
}
