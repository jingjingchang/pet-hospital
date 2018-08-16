package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.SysConfig;
import com.zcmzjp.wx.mapper.SysConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-08-15 16:14
 */
@Service
public class SysConfigService extends BaseService<SysConfig> {

    @Autowired
    SysConfigMapper sysConfigMapper;

    @Override
    public BaseMapper<SysConfig> getMapper() {
        return sysConfigMapper;
    }

    public SysConfig getByCode(String code){
       return sysConfigMapper.getByCode(code);
    }
}
