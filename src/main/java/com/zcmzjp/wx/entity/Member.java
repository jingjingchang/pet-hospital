package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "b_members")
public class Member {
    @Id
    private Integer id;

    private String wxopenid;

    private String name;

    private Integer gender;

    @Column(name = "work_unit")
    private Integer workUnit;

    @Column(name = "identity_no")
    private String identityNo;

    private String email;

    private String title;

    private String address;

    @Column(name = "auth_flag")
    private Boolean authFlag;

    private String mobile;

    private String created;

    @Column(name = "cancel_date")
    private String cancelDate;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
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

    public Integer getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(Integer workUnit) {
        this.workUnit = workUnit;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Boolean getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(Boolean authFlag) {
        this.authFlag = authFlag;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(String cancelDate) {
        this.cancelDate = cancelDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}