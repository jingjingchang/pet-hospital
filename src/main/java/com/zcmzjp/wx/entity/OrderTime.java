package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:09
 * Description:
 */
@Table(name = "b_order_time")
public class OrderTime {

    @Id
    private Integer id;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;


    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
