package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Assets;
import com.zcmzjp.wx.entity.InOut;
import com.zcmzjp.wx.mapper.AssetsMapper;
import com.zcmzjp.wx.mapper.InOutMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2018-01-17.
 */
@Service
public class InOutService extends BaseService<InOut> {
    @Autowired
    InOutMapper inOutMapper;

    @Override
    public BaseMapper<InOut> getMapper() {
        return inOutMapper;
    }

    public List getInOutByTime(String start,String end){
        Map<String,Object> map = new HashMap<>(2);
        map.put("startTime",start);
        map.put("endTime",end);
        return inOutMapper.getInOutByTime(map);
    }

    public List getLastMouthInOut(){
        return inOutMapper.getLastMouthInOut();
    }

    public List getMouthInOutByTime(String start,String end){
        Map<String,Object> map = new HashMap<>(2);
        map.put("startTime",start);
        map.put("endTime",end);
        return inOutMapper.getMouthInOutByTime(map);
    }
}
