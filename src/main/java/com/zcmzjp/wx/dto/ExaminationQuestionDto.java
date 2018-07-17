package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.ExaminationQuestion;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-13 15:44
 */
public class ExaminationQuestionDto extends ExaminationQuestion {

    private String paperName;

    private String questionName;

    private String brief;

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }


    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
