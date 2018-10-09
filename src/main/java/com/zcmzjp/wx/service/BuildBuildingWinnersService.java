package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.BuildBuildingWinners;
import com.zcmzjp.wx.mapper.BuildBuildingWinnersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 14:09
 */
@Service
public class BuildBuildingWinnersService extends BaseService<BuildBuildingWinners> {

    @Autowired
    BuildBuildingWinnersMapper buildBuildingWinnersMapper;

    @Override
    public BaseMapper<BuildBuildingWinners> getMapper() {
        return buildBuildingWinnersMapper;
    }
}
