package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.PartTimeJob;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:17
 * Description:
 */
public interface PartTimeJobMapper extends BaseMapper<PartTimeJob> {

    List<PartTimeJob> getPartTimeJobList();
}
