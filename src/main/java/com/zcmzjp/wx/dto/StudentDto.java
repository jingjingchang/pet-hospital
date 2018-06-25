package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.Student;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-19 9:27
 * Description:
 */
public class StudentDto extends Student {
    private String uname;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
