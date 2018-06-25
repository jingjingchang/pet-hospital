package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.PartTimeJob;
import com.zcmzjp.wx.mapper.PartTimeJobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-02-27 21:28
 * Description:
 */
@Service
public class PartTimeJobService extends BaseService<PartTimeJob>{

    @Autowired
    PartTimeJobMapper partTimeJobMapper;

    @Override
    public BaseMapper<PartTimeJob> getMapper() {
        return partTimeJobMapper;
    }

    public List<PartTimeJob> getPartTimeJobList(){
        return partTimeJobMapper.getPartTimeJobList();
    }
}
