package com.zcmzjp.wx.service;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Apply;
import com.zcmzjp.wx.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplyService extends BaseService<Apply>{

    @Autowired
    ApplyMapper applyMapper;

    @Override
    public BaseMapper<Apply> getMapper()
    {
        return applyMapper;
    }

    public Apply getApplyByOpenId(String openId)
    {
        return applyMapper.getApplyByOpenId(openId);
    }

}
