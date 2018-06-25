package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.UnitTeacher;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-24 9:31
 * Description:
 */
public class UnitTeacherDto extends UnitTeacher {

    private String name;

    private Integer tId;

    private String mobile;

    private Double aLevel;

    private String brief;

    private String title;

    private String picUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Double getaLevel() {
        return aLevel;
    }

    public void setaLevel(Double aLevel) {
        this.aLevel = aLevel;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }
}
