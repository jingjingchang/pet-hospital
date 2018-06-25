package com.zcmzjp.wx.dto;


import com.zcmzjp.wx.entity.Member;

/**
 * Created by Chris on 2017-08-10.
 */
public class MemberDto extends Member {
    private String uname;

    private String ucode;

    private String bname;

    private String gname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUcode() {
        return ucode;
    }

    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }
}
