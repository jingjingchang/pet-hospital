package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.BuildBuilding;
import com.zcmzjp.wx.mapper.BuildBuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 14:09
 */
@Service
public class BuildBuildingService extends BaseService<BuildBuilding> {

    @Autowired
    BuildBuildingMapper buildBuildingMapper;

    @Override
    public BaseMapper<BuildBuilding> getMapper() {
        return buildBuildingMapper;
    }

    public BuildBuilding getNowBuildBuilding(){
      return buildBuildingMapper.getNowBuildBuilding();
    }
}
