package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-30 11:41
 * Description:
 */
@Table(name = "b_wx_config")
public class WxConfig {

    @Id
    private Integer id;

    private Boolean push;

    @Column(name = "graphic_id")
    private Integer graphicId;

    @Column(name = "reply_word")
    private String replyWord;

    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPush() {
        return push;
    }

    public void setPush(Boolean push) {
        this.push = push;
    }

    public Integer getGraphicId() {
        return graphicId;
    }

    public void setGraphicId(Integer graphicId) {
        this.graphicId = graphicId;
    }

    public String getReplyWord() {
        return replyWord;
    }

    public void setReplyWord(String replyWord) {
        this.replyWord = replyWord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
