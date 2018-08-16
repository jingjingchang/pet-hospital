package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.SysConfig;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-08-15 16:09
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

    SysConfig getByCode(@Param("code") String code);
}
