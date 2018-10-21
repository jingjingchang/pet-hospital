package com.zcmzjp.wx.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-10-09 15:50
 */
@Table(name="b_build_building")
public class BuildBuilding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    private String title;

    private String keyword;

    @Column(name = "luck_number")
    private String luckNumber;

    @Column(name = "max_times")
    private String maxTimes;

    private Integer type;

    private String startTime;

    private String endTime;

    private Integer status;

    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getMaxTimes() {
        return maxTimes;
    }

    public void setMaxTimes(String maxTimes) {
        this.maxTimes = maxTimes;
    }
}
