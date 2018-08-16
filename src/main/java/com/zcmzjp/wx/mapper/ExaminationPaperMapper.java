package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.ExaminationPaper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-04 10:07
 */
public interface ExaminationPaperMapper extends BaseMapper<ExaminationPaper> {

    ExaminationPaper getByOpenStatus();
    boolean updateOthersById(@Param("id") Integer id);
}
