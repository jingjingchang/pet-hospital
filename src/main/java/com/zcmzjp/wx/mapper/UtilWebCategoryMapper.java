package com.zcmzjp.wx.mapper;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.UtilWebCategory;

import java.util.List;

public interface UtilWebCategoryMapper extends BaseMapper<UtilWebCategory> {

    List<UtilWebCategory> getAllUtilWebCategorys();
}
