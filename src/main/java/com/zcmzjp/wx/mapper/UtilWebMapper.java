package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.UtilWeb;

import java.util.List;

public interface UtilWebMapper extends BaseMapper<UtilWeb> {

    List<UtilWeb> getAllUtilWebs();
}
