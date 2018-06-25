package com.zcmzjp.wx.mapper;


import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.UserDto;
import com.zcmzjp.wx.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public  interface UserMapper extends BaseMapper<User> {
     User userLogin(Map<String, String> paramMap);

     UserDto getCurrentUserInfo(@Param("id") Integer id);
}
