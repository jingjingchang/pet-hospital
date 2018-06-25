package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Apply;
import com.zcmzjp.wx.entity.Member;
import org.apache.ibatis.annotations.Param;


public interface ApplyMapper extends BaseMapper<Apply>
{
    Apply getApplyByOpenId(@Param("id") String id);
}
