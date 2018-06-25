package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.RoleDto;
import com.zcmzjp.wx.entity.Role;
import com.zcmzjp.wx.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService<Role> {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public BaseMapper<Role> getMapper()
    {
        return roleMapper;
    }

    public List<RoleDto> getRolesByUserId(Integer id)
    {
        return roleMapper.getRolesByUserId(id);
    }

    public List<RoleDto> getAllRoles()
    {
        return roleMapper.getAllRoles();
    }
}
