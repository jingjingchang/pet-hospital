package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.PassDto;
import com.zcmzjp.wx.entity.Pass;
import com.zcmzjp.wx.entity.Student;
import com.zcmzjp.wx.mapper.PassMapper;
import com.zcmzjp.wx.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Chris on 2017-08-09.
 */
@Service
public class PassService extends BaseService<Pass> {

    @Autowired
    PassMapper passMapper;

    @Override
    public BaseMapper<Pass> getMapper() {
        return passMapper;
    }

    public List<PassDto> getListByStuId(Integer stuId){
        return passMapper.getListByStuId(stuId);
    }


}
