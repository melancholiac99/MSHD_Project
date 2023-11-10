package com.example.mshd_project.core.controller;

import com.example.mshd_project.core.service.CodeShowService;
import com.example.mshd_project.core.utils.PageQueryUtil;
import com.example.mshd_project.core.utils.Result;
import com.example.mshd_project.core.utils.ResultGenerator;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class CodeShowController {
    @Resource
    private CodeShowService codeShowService;

    @GetMapping("/codeshow/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        //检查请求参数是否合理，如果不合理返回result的message是"参数异常"
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        //请求合理，则解析请求，将其转化为PageQueryUtil类的对象
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(codeShowService.getCodePage(pageQueryUtil));
    }
}
