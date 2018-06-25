package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.UtilWeb;
import com.zcmzjp.wx.mapper.UtilWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilWebService extends BaseService<UtilWeb> {

    @Autowired
    private UtilWebMapper utilWebMapper;

    @Override
    public BaseMapper<UtilWeb> getMapper() {
        return utilWebMapper;
    }
}
