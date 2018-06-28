package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.StudentDto;
import com.zcmzjp.wx.entity.Student;
import com.zcmzjp.wx.entity.Teacher;
import com.zcmzjp.wx.mapper.StudentMapper;
import com.zcmzjp.wx.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2017-08-09.
 */
@Service
public class StudentService extends BaseService<Student> {

    @Autowired
    StudentMapper studentMapper;

    @Override
    public BaseMapper<Student> getMapper() {
        return studentMapper;
    }

    public List<Student> getAllStudents(){
        return studentMapper.getAllStudents();
    }

    public StudentDto getStudentDtoByPhone(String phone){
        return studentMapper.getStudentDtoByPhone(phone);
    }

    public StudentDto getStudentDtoById(Integer id){
        return studentMapper.getStudentDtoById(id);
    }

    public List<Map<Object,Object>> getStudentGenderStatistics(){
        return studentMapper.getStudentGenderStatistics();
    }

    public List<Map<Object,Object>> getStudentUnitStatistics(){
        return studentMapper.getStudentUnitStatistics();
    }

    public List<Map<Object,Object>> getStudentStudyStatusStatistics(){
        return studentMapper.getStudentStudyStatusStatistics();
    }
}
