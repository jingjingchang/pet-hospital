package com.zcmzjp.wx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.MemberDto;
import com.zcmzjp.wx.dto.TeacherDto;
import com.zcmzjp.wx.entity.Member;
import com.zcmzjp.wx.entity.Teacher;
import com.zcmzjp.wx.mapper.MemberMapper;
import com.zcmzjp.wx.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2017-08-09.
 */
@Service
public class TeacherService extends BaseService<Teacher> {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public BaseMapper<Teacher> getMapper() {
        return teacherMapper;
    }

    public List<Teacher> getAllTeachers(){
        return teacherMapper.getAllTeachers();
    }

    public Teacher teacherLogin(String username,String pwd){
        return teacherMapper.teacherLogin(username,pwd);
    }

    public List<Teacher> getTeacherListWithOutByUnitId(Integer unitId){
        return teacherMapper.getTeacherListWithOutByUnitId(unitId);
    }

    public PageInfo getTeacherExamResultList(Map<String,Object> map){
            List<TeacherDto> data = null;
            if (map != null) {
                PageHelper.startPage(map);
                data =teacherMapper.getTeacherExamResultList(map);
            } else {
                data = new ArrayList<>();
            }
            return new PageInfo<TeacherDto>(data);
    }


}
