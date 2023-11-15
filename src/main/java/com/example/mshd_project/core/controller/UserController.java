package com.example.mshd_project.core.controller;


import com.example.mshd_project.core.entity.User;
import com.example.mshd_project.core.service.UserService;
import com.example.mshd_project.core.utils.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


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

    @CrossOrigin
    @GetMapping("/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        //检查请求参数是否合理，如果不合理返回result的message是"参数异常"
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        //请求合理，则解析请求，将其转化为PageQueryUtil类的对象
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageResult codeShowPageResult = userService.getUserPage(pageQueryUtil);
        if(codeShowPageResult.getList() != null && codeShowPageResult.getList().size()!=0){
            return ResultGenerator.genSuccessResult(codeShowPageResult);
        }
        else {
            return ResultGenerator.genFailResult("未找到数据，或者是返回数据列表没有初始化！");
        }
    }
}
