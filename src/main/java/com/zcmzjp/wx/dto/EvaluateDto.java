package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.Evaluate;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-13 14:30
 * Description:
 */
public class EvaluateDto extends Evaluate {

    private String name;

    private Integer gender;

    private String mobile;

    private String email;


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
}
