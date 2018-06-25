package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.EvaluateDto;
import com.zcmzjp.wx.entity.Evaluate;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-03-13 11:27
 * Description:
 */
public interface EvaluateMapper extends BaseMapper<Evaluate> {
    List<EvaluateDto> getEvaluateByTeaId(@Param("teaId") Integer teaId);

    Evaluate getEvaluateByStuIdAndTeaId(@Param("stuId") Integer stuId,@Param("teaId") Integer teaId);
}
