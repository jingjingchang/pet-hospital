package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.Assets;

/**
 * Created by Chris on 2018-01-17.
 */
public class AssetsDto extends Assets {

    private String pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
