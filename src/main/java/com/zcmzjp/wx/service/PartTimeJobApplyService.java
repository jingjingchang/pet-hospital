package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.PartTimeJobApply;
import com.zcmzjp.wx.mapper.PartTimeJobApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:29
 * Description:
 */
@Service
public class PartTimeJobApplyService extends BaseService<PartTimeJobApply> {

    @Autowired
    PartTimeJobApplyMapper partTimeJobApplyMapper;

    @Override
    public BaseMapper<PartTimeJobApply> getMapper() {
        return partTimeJobApplyMapper;
    }

    public PartTimeJobApply getJobApplyByJobAndMId(Integer mId,Integer jId){
        return partTimeJobApplyMapper.getJobApplyByJobAndMId(mId,jId);
    }
}
