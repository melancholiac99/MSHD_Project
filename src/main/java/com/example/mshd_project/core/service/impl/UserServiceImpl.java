package com.example.mshd_project.core.service.impl;

import com.example.mshd_project.core.dao.UserMapper;
import com.example.mshd_project.core.domain.User;
import com.example.mshd_project.core.service.UserService;
import com.example.mshd_project.core.utils.PageQueryUtil;
import com.example.mshd_project.core.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageResult getUserPage(PageQueryUtil pageUtil) {

        List<User> userList = userMapper.findUserList(pageUtil);
        int total = userMapper.getTotalUsers(pageUtil);
        PageResult pageResult = new PageResult(userList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
