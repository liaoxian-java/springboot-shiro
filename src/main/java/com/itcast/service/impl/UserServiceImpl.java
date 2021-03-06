package com.itcast.service.impl;

import com.itcast.mapper.UserMapper;
import com.itcast.pojo.User;
import com.itcast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User queryUserByName(String name){
        return userMapper.queryUserByName(name);
    }
}
