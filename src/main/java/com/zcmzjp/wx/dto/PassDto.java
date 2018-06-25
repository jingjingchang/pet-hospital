package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.Pass;

/**
 * Created by Chris on 2018-01-15.
 */
public class PassDto extends Pass {
    private String sname;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
}
