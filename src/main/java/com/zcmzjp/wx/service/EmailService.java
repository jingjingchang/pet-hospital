package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Email;
import com.zcmzjp.wx.mapper.EmailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 14:09
 */
@Service
public class EmailService extends BaseService<Email> {

    @Autowired
    EmailMapper emailMapper;

    @Override
    public BaseMapper<Email> getMapper() {
        return emailMapper;
    }
}
