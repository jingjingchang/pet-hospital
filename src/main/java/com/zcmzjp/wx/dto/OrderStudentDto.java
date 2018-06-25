package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.OrderStudent;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 9:38
 * Description:
 */
public class OrderStudentDto extends OrderStudent {

    private String stuName;

    private String smobile;

    private String tmobile;

    private String teachName;

    private String oTime;

    private String uname;

    private String pickup;

    private Integer orderNum;

    private Integer successNum;

    private String wxOpenId;

    public String getSmobile() {
        return smobile;
    }

    public void setSmobile(String smobile) {
        this.smobile = smobile;
    }

    public String getTmobile() {
        return tmobile;
    }

    public void setTmobile(String tmobile) {
        this.tmobile = tmobile;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getTeachName() {
        return teachName;
    }

    public void setTeachName(String teachName) {
        this.teachName = teachName;
    }

    public String getoTime() {
        return oTime;
    }

    public void setoTime(String oTime) {
        this.oTime = oTime;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(Integer successNum) {
        this.successNum = successNum;
    }


    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
}
