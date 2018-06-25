package com.zcmzjp.wx.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Chris
 * @date 2017/7/19.
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {

    public List<T> listByPage(Map<String, Object> map);

    public List<T> listPageById(Map<String, Object> map);

 }
