package com.example.mshd_project.core.service;

import com.example.mshd_project.core.domain.User;
import com.example.mshd_project.core.utils.PageQueryUtil;
import com.example.mshd_project.core.utils.PageResult;


public interface UserService {

    User selectByName(String userName);

    int insert(User user);

    int updatePwd(String username, String md5Encode);

    PageResult getUserPage(PageQueryUtil pageQueryUtil);
}
