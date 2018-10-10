package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.BuildBuildingInfo;
import com.zcmzjp.wx.mapper.BuildBuildingInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 14:09
 */
@Service
public class BuildBuildingInfoService extends BaseService<BuildBuildingInfo> {

    @Autowired
    BuildBuildingInfoMapper buildBuildingInfoMapper;

    @Override
    public BaseMapper<BuildBuildingInfo> getMapper() {
        return buildBuildingInfoMapper;
    }

    public Integer insertByNumber(BuildBuildingInfo buildBuildingInfo){
        return  buildBuildingInfoMapper.insertByNumber(buildBuildingInfo);
    }
}
