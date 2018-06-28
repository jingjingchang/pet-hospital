package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.StudentDto;
import com.zcmzjp.wx.entity.Student;
import com.zcmzjp.wx.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student> {
     List<Student> getAllStudents();

     StudentDto getStudentDtoByPhone(@Param("phone") String phone);

     StudentDto getStudentDtoById(@Param("id") Integer id);

     List<Map<Object,Object>> getStudentGenderStatistics();

     List<Map<Object,Object>> getStudentUnitStatistics();

     List<Map<Object,Object>> getStudentStudyStatusStatistics();

}