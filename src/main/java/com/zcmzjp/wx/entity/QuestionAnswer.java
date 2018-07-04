package com.zcmzjp.wx.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 9:51
 */
@Table(name="b_question_answer")
public class QuestionAnswer {

    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
