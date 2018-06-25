package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.PassDto;
import com.zcmzjp.wx.entity.Pass;
import com.zcmzjp.wx.entity.Student;

import java.util.List;

public interface PassMapper extends BaseMapper<Pass> {
     List<PassDto> getListByStuId(Integer stuId);
}