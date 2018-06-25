package com.zcmzjp.wx.service;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.WxConfig;
import com.zcmzjp.wx.mapper.WxConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-30 11:44
 * Description:
 */
@Service
public class WxConfigService extends BaseService<WxConfig> {

    @Autowired
    WxConfigMapper wxConfigMapper;

    @Override
    public BaseMapper<WxConfig> getMapper() {
        return wxConfigMapper;
    }
}
