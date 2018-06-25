package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.UnitTeacherDto;
import com.zcmzjp.wx.entity.UnitTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-24 9:27
 * Description:
 */
public interface UnitTeacherMapper extends BaseMapper<UnitTeacher> {

    List<UnitTeacherDto> getTeacherListByUnitId(@Param("unitId") Integer unitId);
}
