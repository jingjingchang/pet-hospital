package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Unit;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-17 14:59
 * Description:
 */
public interface UnitMapper extends BaseMapper<Unit> {
    List<Unit> getAllUnit();
}
