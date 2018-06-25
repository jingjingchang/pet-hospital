package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.Teacher;

import java.util.zip.DeflaterOutputStream;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-12 21:50
 * Description:
 */
public class TeacherDto extends Teacher {

    private Double aLevel;

    private String uname;

    public Double getaLevel() {
        return aLevel;
    }

    public void setaLevel(Double aLevel) {
        this.aLevel = aLevel;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
