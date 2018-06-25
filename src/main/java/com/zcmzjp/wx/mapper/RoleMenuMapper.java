package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.RoleMenu;
import org.apache.ibatis.annotations.Param;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    boolean delRoleMenusByRoleId(@Param("id") Integer paramInteger);
}
