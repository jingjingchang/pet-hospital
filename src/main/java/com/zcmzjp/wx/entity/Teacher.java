package com.zcmzjp.wx.entity;

import com.zcmzjp.wx.dto.TeacherDto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * Created by Chris on 2018-01-11.
 */
@Table(name ="b_teachers")
public class Teacher {
    @Id
    private Integer id;

    @Column(name = "unit_id")
    private Integer unitId;

    private String name;

    private Integer gender;

    private String mobile;

    private String number;

    private String brief;

    private String email;

    private Integer status;

    @Column(name = "sub_three")
    private Integer subThree;

    @Column(name = "sub_two")
    private Integer subTwo;

    private String title;

    private String picUrl;

    private String identityNo;

    private String pwd;

    private Date created;

    @Transient
    private Integer tId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Integer getSubThree() {
        return subThree;
    }

    public void setSubThree(Integer subThree) {
        this.subThree = subThree;
    }

    public Integer getSubTwo() {
        return subTwo;
    }

    public void setSubTwo(Integer subTwo) {
        this.subTwo = subTwo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
