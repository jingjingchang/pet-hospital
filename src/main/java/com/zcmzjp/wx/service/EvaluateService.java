package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.EvaluateDto;
import com.zcmzjp.wx.entity.Evaluate;
import com.zcmzjp.wx.mapper.EvaluateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-13 14:32
 * Description:
 */
@Service
public class EvaluateService extends BaseService<Evaluate> {
    @Autowired
    EvaluateMapper evaluateMapper;

    @Override
    public BaseMapper<Evaluate> getMapper() {
        return evaluateMapper;
    }

    public List<EvaluateDto> getEvaluateByTeaId(Integer teaId){
        return  evaluateMapper.getEvaluateByTeaId(teaId);
    }

    public Evaluate getEvaluateByStuIdAndTeaId(Integer stuId,Integer teaId){
        return evaluateMapper.getEvaluateByStuIdAndTeaId(stuId,teaId);
    }
}
