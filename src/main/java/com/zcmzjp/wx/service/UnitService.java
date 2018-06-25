package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Unit;
import com.zcmzjp.wx.mapper.UnitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-17 15:00
 * Description:
 */

@Service
public class UnitService extends BaseService<Unit> {
    @Autowired
    UnitMapper unitMapper;

    @Override
    public BaseMapper<Unit> getMapper() {
        return unitMapper;
    }

    public List<Unit> getAllUnit(){
        return unitMapper.getAllUnit();
    }
}
