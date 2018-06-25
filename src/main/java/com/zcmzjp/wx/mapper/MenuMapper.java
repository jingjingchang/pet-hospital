package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.MenuDto;
import com.zcmzjp.wx.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    List<MenuDto> getAllMenus();

    List<MenuDto> getAllMenusByRoleId(@Param("id") Integer paramInteger);

    List<MenuDto> getMenusByUserId(@Param("id") Integer paramInteger);
}
