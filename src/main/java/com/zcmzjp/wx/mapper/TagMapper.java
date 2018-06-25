package com.zcmzjp.wx.mapper;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Tag;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> getAllTag();
}
