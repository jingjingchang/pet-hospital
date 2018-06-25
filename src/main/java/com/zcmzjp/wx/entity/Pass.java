package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Chris on 2018-01-15.
 */
@Table(name="b_pass")
public class Pass {
    @Id
    private Integer id;

    @Column(name ="stu_id")
    private Integer stuId;

    @Column(name="sub_type")
    private Integer subType;

    @Column(name ="is_pass")
    private Integer isPass;

    @Column(name = "test_time")
    private String testTime;

    private double spend;

    private String evaluate;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
