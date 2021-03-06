package com.zcmzjp.wx.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 13:52
 */
@Table(name = "b_email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    private String subject;

    private String sender;

    @Column(name = "sender_name")
    private String senderName;

    private String addressee;

    private String content;

    private Integer status;

    private Date created;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
