package com.zcmzjp.wx.mapper;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.MemberDto;
import com.zcmzjp.wx.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberMapper extends BaseMapper<Member> {

    Member getMemberByOpenId(@Param("id") String id);

     List<Member> getAllMembers();
    //获取所有已经认证的用户
     List<Member> getAllTrueMembers();

     List<Map<String,Object>> getMembersSexStatistics(@Param("id") Integer id);

     List<Map<String,Object>> getMembersFlagStatistics(@Param("id") Integer id);

}