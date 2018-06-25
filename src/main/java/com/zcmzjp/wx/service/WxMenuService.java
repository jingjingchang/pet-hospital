package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.WxMenuDto;
import com.zcmzjp.wx.entity.WxMenu;
import com.zcmzjp.wx.mapper.WxMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chris on 2017-08-04.
 */
@Service
public class WxMenuService extends BaseService<WxMenu> {
    @Autowired
    WxMenuMapper wxMenuMapper;
    @Override
    public BaseMapper<WxMenu> getMapper() {
        return wxMenuMapper;
    }

    public List<WxMenuDto> getAllMenus(){
        return wxMenuMapper.getAllMenus();
    }
}
