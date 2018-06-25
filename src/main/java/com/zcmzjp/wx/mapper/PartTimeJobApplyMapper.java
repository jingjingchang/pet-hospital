package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.PartTimeJobApply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:31
 * Description:
 */
public interface PartTimeJobApplyMapper extends BaseMapper<PartTimeJobApply>{

    List<PartTimeJobApply> getJobApplyListByJobId(@Param("id") Integer id);

    PartTimeJobApply getJobApplyByJobAndMId(@Param("mId") Integer mId,@Param("jId") Integer jId);
}
