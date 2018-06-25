package com.zcmzjp.wx.mapper;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.WxMenuDto;
import com.zcmzjp.wx.entity.WxMenu;

import java.util.List;

public interface WxMenuMapper extends BaseMapper<WxMenu> {
     List<WxMenuDto> getAllMenus();
}