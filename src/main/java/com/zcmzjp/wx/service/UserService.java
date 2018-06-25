package com.zcmzjp.wx.service;

import com.zcmzjp.wx.config.BaseMapper;
import com.zcmzjp.wx.dto.UserDto;
import com.zcmzjp.wx.entity.User;
import com.zcmzjp.wx.mapper.UserMapper;
import com.zcmzjp.wx.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    UserMapper mapper;

    @Override
    public BaseMapper<User> getMapper()
    {
        return this.mapper;
    }

    public User userLogin(String name, String password)
    {
        String s = SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(password));
        Map<String, String> map = new HashMap();
        map.put("username", name);
        map.put("password", s);
        return mapper.userLogin(map);
    }

    public UserDto getCurrentUserInfo(Integer id){
        return mapper.getCurrentUserInfo(id);
    }
}
