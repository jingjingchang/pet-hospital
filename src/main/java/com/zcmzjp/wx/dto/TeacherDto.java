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

    private Integer yesNum;

    private String noNum;

    //合格率
    private double okRate;

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

    public Integer getYesNum() {
        return yesNum;
    }

    public void setYesNum(Integer yesNum) {
        this.yesNum = yesNum;
    }

    public String getNoNum() {
        return noNum;
    }

    public void setNoNum(String noNum) {
        this.noNum = noNum;
    }



    public double getOkRate() {
        return okRate;
    }

    public void setOkRate(double okRate) {
        this.okRate = okRate;
    }
}
