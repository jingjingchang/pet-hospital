package com.zcmzjp.wx.dto;


import com.zcmzjp.wx.entity.Role;

public class RoleDto extends Role {
    private String uId;

    public String getuId()
    {
        return this.uId;
    }

    public void setuId(String uId)
    {
        this.uId = uId;
    }
}
