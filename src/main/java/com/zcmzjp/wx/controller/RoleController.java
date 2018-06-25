package com.zcmzjp.wx.controller;


import com.zcmzjp.wx.controller.BaseController;
import com.zcmzjp.wx.dto.RoleDto;
import com.zcmzjp.wx.entity.Role;
import com.zcmzjp.wx.service.BaseService;
import com.zcmzjp.wx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping({"/admin/sys/role"})
@Controller
public class RoleController extends BaseController<Role> {
    @Autowired
    RoleService roleService;

    @Override
    public BaseService<Role> getService()
    {
        return this.roleService;
    }

    @Override
    public String getViewPrefix()
    {
        return "role";
    }

    @RequestMapping({"/getRolesByUserId"})
    @ResponseBody
    public List<RoleDto> getRolesByUserId(Integer id)
    {
        List<RoleDto> roles = roleService.getRolesByUserId(id);

        return roles;
    }

    @RequestMapping({"/getAllRoles"})
    @ResponseBody
    public List<RoleDto> getAllRoles()
    {
        List<RoleDto> roles = roleService.getAllRoles();
        return roles;
    }
}
