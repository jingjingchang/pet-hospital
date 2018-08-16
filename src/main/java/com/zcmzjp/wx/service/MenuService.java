package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.MenuDto;
import com.zcmzjp.wx.entity.Menu;
import com.zcmzjp.wx.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService  extends BaseService<Menu> {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public BaseMapper<Menu> getMapper()
    {
        return menuMapper;
    }

    public List<MenuDto> getAllMenus()
    {
        return menuMapper.getAllMenus();
    }

    public List<MenuDto> getAllMenusByRoleId(Integer id)
    {
        return menuMapper.getAllMenusByRoleId(id);
    }

    public List<MenuDto> getMenusByUserId(Integer id)
    {
        return menuMapper.getMenusByUserId(id);
    }
}
