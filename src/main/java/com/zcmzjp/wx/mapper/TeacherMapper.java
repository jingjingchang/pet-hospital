package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper extends BaseMapper<Teacher> {

     List<Teacher> getAllTeachers();

     Teacher teacherLogin(@Param("username") String username,@Param("pwd") String pwd);

     List<Teacher> getTeacherListWithOutByUnitId(@Param("unitId") Integer unitId);

}