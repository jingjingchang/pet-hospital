package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.BuildBuilding;
import com.zcmzjp.wx.entity.BuildBuildingInfo;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 14:10
 */
public interface BuildBuildingInfoMapper extends BaseMapper<BuildBuildingInfo> {
    public Integer insertByNumber(BuildBuildingInfo buildBuildingInfo);
}
