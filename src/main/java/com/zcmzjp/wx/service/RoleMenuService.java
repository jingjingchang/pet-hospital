package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.RoleMenu;
import com.zcmzjp.wx.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuService extends BaseService<RoleMenu>{
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public BaseMapper<RoleMenu> getMapper()
    {
        return roleMenuMapper;
    }

    public boolean delRoleMenusByRoleId(Integer id)
    {
        return roleMenuMapper.delRoleMenusByRoleId(id);
    }
}
