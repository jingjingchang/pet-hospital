package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Assets;
import com.zcmzjp.wx.entity.InOut;

import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2018-01-17.
 */
public interface InOutMapper extends BaseMapper<InOut> {

    List<Map<String,Object>> getInOutByTime(Map<String,Object> map);

    List<Map<String,Object>> getLastMouthInOut();

    List<Map<String,Object>> getMouthInOutByTime(Map<String,Object> map);

}
