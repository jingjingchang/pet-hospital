package com.chris.pethospital.service;

import com.chris.pethospital.entity.User;
import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.mapper.UserMapper;
import com.chris.pethospital.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends BaseService<User> {

    @Autowired
    UserMapper userMapper;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    public User userLogin(String name, String password)
    {
        String s = SHA256Util.getSHA256(SHA256Util.getSHA256MixPassword(password));
        Map<String, String> map = new HashMap();
        map.put("username", name);
        map.put("password", s);
        return userMapper.userLogin(map);
    }

    public User getByUsername(String username){
       return userMapper.getByUsername(username);
    }
}
