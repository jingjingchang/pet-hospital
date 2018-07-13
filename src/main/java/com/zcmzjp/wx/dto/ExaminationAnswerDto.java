package com.zcmzjp.wx.dto;

import com.zcmzjp.wx.entity.ExaminationAnswer;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:37
 */
public class ExaminationAnswerDto extends ExaminationAnswer {

    private String paperName;

    private String questionName;

    private String teacherName;

    private String studentName;


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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
