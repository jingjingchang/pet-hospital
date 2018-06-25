package com.zcmzjp.wx.entity;

import java.io.Serializable;

/**
 * TODO
 *
 * @author Administrator
 * @date 2017/7/19.
 */
public class Message implements Serializable{

    private boolean status;

    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
