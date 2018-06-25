package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.WxGraphic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxGraphicMapper extends BaseMapper<WxGraphic> {

     List<WxGraphic> getAllGraphics();

     Integer addReadNum(@Param("id") Integer id);
}