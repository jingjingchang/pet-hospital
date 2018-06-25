package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.PartTimeJob;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-02 16:48
 * Description:
 */
public class PartTimeJobDto extends PartTimeJob {

    private Integer applyNum;


    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }
}
