package com.example.mshd_project.core.service.impl;

import com.example.mshd_project.core.dao.UserMapper;
import com.example.mshd_project.core.entity.User;
import com.example.mshd_project.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zyt
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;

    @Override
    public User selectByName(String userName) {
        return userMapper.getUserByName(userName);
    }

    @Override
    public int insert(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updatePwd(String username, String newpassword) {
        return userMapper.updateUser(username,newpassword);
    }
}
