package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.RoleDto;
import com.zcmzjp.wx.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<RoleDto> getRolesByUserId(@Param("id") Integer paramInteger);

    List<RoleDto> getAllRoles();
}
