package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Chris on 2018-01-11.
 */
@Table(name = "b_students")
public class Student {
    @Id
    private Integer id;

    @Column(name = "m_id")
    private Integer mId;

    private String name;

    private Integer gender;

    private String mobile;

    private String email;

    private String insurance;

    private Integer qq;

    @Column(name = "enroll_time")
    private String enrollTime;

    @Column(name = "identity_no")
    private String identityNo;

    private Double tuition;

    @Column(name = "unit_id")
    private Integer unitId;

    private String school;

    @Column(name = "dorm_no")
    private String dormNo;

    @Column(name = "study_status")
    private Integer studyStatus;

    private Date created;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
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

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
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

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public String getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(String enrollTime) {
        this.enrollTime = enrollTime;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public Double getTuition() {
        return tuition;
    }

    public void setTuition(Double tuition) {
        this.tuition = tuition;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDormNo() {
        return dormNo;
    }

    public void setDormNo(String dormNo) {
        this.dormNo = dormNo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public Integer getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }
}
