package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Assets;
import com.zcmzjp.wx.mapper.AssetsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Chris on 2018-01-17.
 */
@Service
public class AssetsService extends BaseService<Assets> {
    @Autowired
    AssetsMapper assetsMapper;
    @Override
    public BaseMapper<Assets> getMapper() {
        return assetsMapper;
    }
}
