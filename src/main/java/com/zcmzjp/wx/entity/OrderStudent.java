package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 9:33
 * Description:
 */

@Table(name = "b_order_student")
public class OrderStudent {
    @Id
    private Integer id;

    @Column(name = "od_id")
    private Integer odId;

    @Column(name = "stu_id")
    private Integer stuId;

    @Column(name = "teach_id")
    private Integer teachId;

    private Integer subType;

    private Integer status;

    private Date created;

    private Integer sms;

    @Column(name = "final_pickup")
    private String finalPickup;

    @Column(name = "final_time")
    private String finalTime;

    @Column(name = "pickup_time")
    private String pickupTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOdId() {
        return odId;
    }

    public void setOdId(Integer odId) {
        this.odId = odId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getTeachId() {
        return teachId;
    }

    public void setTeachId(Integer teachId) {
        this.teachId = teachId;
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

    public Integer getSms() {
        return sms;
    }

    public void setSms(Integer sms) {
        this.sms = sms;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getFinalPickup() {
        return finalPickup;
    }

    public void setFinalPickup(String finalPickup) {
        this.finalPickup = finalPickup;
    }

    public String getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }
}
