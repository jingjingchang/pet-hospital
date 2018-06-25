package com.zcmzjp.wx.entity;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "b_apply")
public class Apply {

    @Id
    private Integer id;

    private String name;

    @Column(name = "m_id")
    private Integer mId;

    private Integer gender;

    private String mobile;

    private Integer qq;

    private String email;

    private String address;

    @Column(name = "apply_ask")
    private String applyAsk;

    @Column(name = "apply_date")
    private String applyDate;

    private Integer confirm;

    private Integer status;

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

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
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

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApplyAsk() {
        return applyAsk;
    }

    public void setApplyAsk(String applyAsk) {
        this.applyAsk = applyAsk;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
