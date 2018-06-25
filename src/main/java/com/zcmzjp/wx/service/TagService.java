package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Tag;
import com.zcmzjp.wx.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService extends BaseService<Tag> {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public BaseMapper<Tag> getMapper() {
        return tagMapper;
    }
}
