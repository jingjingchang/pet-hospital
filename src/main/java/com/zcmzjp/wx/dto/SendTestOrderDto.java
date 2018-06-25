package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.SendTestOrder;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-05-25 10:33
 * Description:
 */
public class SendTestOrderDto extends SendTestOrder {

    private String studentName;

    private Integer gender;

    private String mobile;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
}
