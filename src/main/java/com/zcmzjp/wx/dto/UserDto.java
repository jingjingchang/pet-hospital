package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.User;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-05 17:41
 * Description:
 */
public class UserDto extends User {
    private String rname;

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
