package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Questions;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-13 15:36
 */
public interface QuestionsMapper extends BaseMapper<Questions> {
    List<Questions> getQuestionsNotInPaper(@Param("paperId") String paperId);
}
