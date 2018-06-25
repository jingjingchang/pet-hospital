package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.UnitTeacherDto;
import com.zcmzjp.wx.entity.UnitTeacher;
import com.zcmzjp.wx.mapper.UnitTeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-24 9:28
 * Description:
 */
@Service
public class UnitTeacherService extends  BaseService<UnitTeacher> {

    @Autowired
    UnitTeacherMapper unitTeacherMapper;

    @Override
    public BaseMapper<UnitTeacher> getMapper() {
        return unitTeacherMapper;
    }

    public List<UnitTeacherDto> getTeacherListByUnitId(Integer unitId){
         return unitTeacherMapper.getTeacherListByUnitId(unitId);
    }
}
