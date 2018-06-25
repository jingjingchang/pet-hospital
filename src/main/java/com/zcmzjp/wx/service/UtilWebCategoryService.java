package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.UtilWebCategory;
import com.zcmzjp.wx.mapper.UtilWebCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilWebCategoryService extends BaseService<UtilWebCategory> {

    @Autowired
    UtilWebCategoryMapper utilWebCategoryMapper;

    @Override
    public BaseMapper<UtilWebCategory> getMapper() {
        return utilWebCategoryMapper;
    }

}
