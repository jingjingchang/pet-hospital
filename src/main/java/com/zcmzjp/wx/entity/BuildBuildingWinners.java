package com.zcmzjp.wx.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-10-09 15:50
 */
@Table(name="b_build_building_winners")
public class BuildBuildingWinners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    private String wxopenid;

    @Column(name = "build_info_id")
    private String buildInfoId;

    private String name;

    private Integer gender;

    private Integer mobile;

    private String address;

    private Integer status;

    private Date created;


    @Transient
    private String title;

    @Transient
    private String luckNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public String getBuildInfoId() {
        return buildInfoId;
    }

    public void setBuildInfoId(String buildInfoId) {
        this.buildInfoId = buildInfoId;
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

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLuckNumber() {
        return luckNumber;
    }

    public void setLuckNumber(String luckNumber) {
        this.luckNumber = luckNumber;
    }
}
