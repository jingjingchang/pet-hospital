package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.TeacherDto;
import com.zcmzjp.wx.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TeacherMapper extends BaseMapper<Teacher> {

     List<Teacher> getAllTeachers();

     Teacher teacherLogin(@Param("username") String username,@Param("pwd") String pwd);

     List<Teacher> getTeacherListWithOutByUnitId(@Param("unitId") Integer unitId);

     List<TeacherDto> getTeacherExamResultList(Map<String,Object> map);

}