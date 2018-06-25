package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.MemberDto;
import com.zcmzjp.wx.entity.Member;
import com.zcmzjp.wx.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Chris on 2017-08-09.
 */
@Service
public class MemberService extends BaseService<Member> {
    @Autowired
    MemberMapper memberMapper;
    @Override
    public BaseMapper<Member> getMapper() {
        return memberMapper;
    }

    public Member getMemberByOpenId(String id){
       return memberMapper.getMemberByOpenId(id);
    }

    public List<Member> getAllMembers(){
        return memberMapper.getAllMembers();
    }

    public List<Member> getAllTrueMembers(){
        return memberMapper.getAllTrueMembers();
    }

    public List<Map<String,Object>> getMembersSexStatistics(Integer id){
        return memberMapper.getMembersSexStatistics(id);
    }

    public List<Map<String,Object>> getMembersFlagStatistics(Integer id){
        return memberMapper.getMembersFlagStatistics(id);
    }

}
