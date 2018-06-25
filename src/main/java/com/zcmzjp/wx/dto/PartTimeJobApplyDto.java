package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.PartTimeJobApply;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-10 11:36
 * Description:
 */
public class PartTimeJobApplyDto extends PartTimeJobApply {
    private String name;

    private Integer gender;

    private String mobile;

    private String email;

    //平均分
    private Double ascore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAscore() {
        return ascore;
    }

    public void setAscore(Double ascore) {
        this.ascore = ascore;
    }
}
