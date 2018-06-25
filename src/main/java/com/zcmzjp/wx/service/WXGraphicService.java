package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.JsonResult;
import com.zcmzjp.wx.entity.WxGraphic;
import com.zcmzjp.wx.mapper.WxGraphicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chris on 2017-08-03.
 */
@Service
public class WXGraphicService extends BaseService<WxGraphic> {

    @Autowired
    WxGraphicMapper wxGraphicMapper;

    @Override
    public BaseMapper<WxGraphic> getMapper() {
        return wxGraphicMapper;
    }

    public List<WxGraphic> getAllGraphics(){
        return wxGraphicMapper.getAllGraphics();
    }

    public Integer addReadNum(Integer id){
        return wxGraphicMapper.addReadNum(id);
    }
}
