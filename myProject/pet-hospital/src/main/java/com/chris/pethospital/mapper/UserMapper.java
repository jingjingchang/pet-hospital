package com.chris.pethospital.mapper;

import com.chris.pethospital.config.BaseMapper;
import com.chris.pethospital.entity.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    User userLogin(Map<String, String> paramMap);

    User getByUsername(String username);
}
