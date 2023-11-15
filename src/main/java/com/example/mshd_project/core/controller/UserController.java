package com.example.mshd_project.core.controller;


import com.example.mshd_project.core.entity.User;
import com.example.mshd_project.core.service.UserService;
import com.example.mshd_project.core.utils.MD5Util;
import com.example.mshd_project.core.utils.Result;
import com.example.mshd_project.core.utils.ResultGenerator;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @CrossOrigin
    public Result register(@RequestParam String userName,
                           @RequestParam String passwd) {

        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passwd)) {
            return ResultGenerator.genFailResult("用户名和密码不能为空");
        }
        // 验证用户名是否已经注册
        User exsitUser = userService.selectByName(userName);
        if (exsitUser != null) {
            return ResultGenerator.genFailResult("该用户已存在");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        User user = new User();
        user.setUserName(userName);
        user.setRegistTime(format);
        user.setStatus("1");
        user.setPassword(MD5Util.MD5Encode(passwd,"UTF-8") );
        int count = userService.insert(user);
        System.out.println(count);
        if (count != 1) {
           return ResultGenerator.genFailResult("注册失败");
        }
        return ResultGenerator.genSuccessResult("注册成功");
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @CrossOrigin
    public Result login(@RequestParam String userName,
                        @RequestParam String passwd,
                        HttpSession session,
                        HttpServletResponse response) {
        User exsitUser = userService.selectByName(userName);
        if (exsitUser == null) {
            return ResultGenerator.genFailResult("用户未注册");
        }
        if (!exsitUser.getPassword().equals(MD5Util.MD5Encode(passwd,"UTF-8"))) {
            return ResultGenerator.genFailResult("密码有误，请重新输入");
        }
        session.setAttribute("username", userName);
        return ResultGenerator.genSuccessResult("登录成功");
    }

    @RequestMapping(value = "/changepwd", method = RequestMethod.POST)
    @CrossOrigin
    public Result   changepwd(@RequestParam String userName,@RequestParam String oldpassword,@RequestParam String newpassword, HttpSession session) {
        User exsitUser = userService.selectByName(userName);
        if (exsitUser == null) {
            return ResultGenerator.genFailResult("用户未注册");
        }
        if (!MD5Util.MD5Encode(oldpassword,"UTF-8").equals(exsitUser.getPassword())) {
            System.out.println(oldpassword);
            return ResultGenerator.genFailResult("原密码输入错误");
        }else {
            int count = userService.updatePwd(userName,MD5Util.MD5Encode(newpassword,"UTF-8"));
            if (count>0) {
                return ResultGenerator.genSuccessResult("密码修改成功");
            }
            return ResultGenerator.genFailResult("密码修改错误");
        }
    }
}
